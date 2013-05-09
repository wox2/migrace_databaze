/**
 * \file pgr.js
 * \brief Javascript version of the pgr-framework.
 * \author Tomas Barak
 *
 * Based on http://learningwebgl.com and the C++ version of the framework.
 */

var pgr = {};

pgr.log = function(msg) {
  alert(msg);
};

pgr.createShaderFromSource = function(type, src) {
  var shader = gl.createShader(type);
  gl.shaderSource(shader, src);
  gl.compileShader(shader);

  if(!gl.getShaderParameter(shader, gl.COMPILE_STATUS)) {
    pgr.log(gl.getShaderInfoLog(shader));
    return null;
  }

  return shader;
};

pgr.createShaderFromElement = function(elementId) {
  var shaderScript = document.getElementById(elementId);
  if(!shaderScript)
    return null;

  var src = "";
  var k = shaderScript.firstChild;
  while(k) {
    if(k.nodeType == 3)
      src += k.textContent;
    k = k.nextSibling;
  }

  switch(shaderScript.type) {
    case "x-shader/x-fragment":
      return pgr.createShaderFromSource(gl.FRAGMENT_SHADER, src);
    case "x-shader/x-vertex":
      return pgr.createShaderFromSource(gl.VERTEX_SHADER, src);
  }
  return null;
};

pgr.createProgram = function(shaders) {
  var shaderProgram = gl.createProgram();
  for(var sh in shaders)
    gl.attachShader(shaderProgram, shaders[sh]);
  gl.linkProgram(shaderProgram);
  if(!gl.getProgramParameter(shaderProgram, gl.LINK_STATUS)) {
    pgr.log("Could not link shaders");
    pgr.deleteProgramAndShaders(shaderProgram);
    return null;
  }
  return shaderProgram;
};

pgr.deleteProgramAndShaders = function(shaderProgram) {
  /// TODO: implement
};

pgr.createCheckerboard = function(colors) {
  var w = 2; var h = 2;
  var canvas = document.createElement("canvas");
  var ctx = canvas.getContext("2d");
  var imgData = ctx.createImageData(w, h);
  for (var i = 0; i < imgData.data.length; ++i) {
    var c = ((i % w) + Math.floor(i / w) % 2) % 2;
    imgData.data[i * 4 + 0] = colors[c][0];
    imgData.data[i * 4 + 1] = colors[c][1];
    imgData.data[i * 4 + 2] = colors[c][2];
    imgData.data[i * 4 + 3] = colors[c][3];
  };
  return imgData;
}

pgr.createTexture = function(url, mipmaps, target) {
  target = typeof target == "undefined" ? gl.TEXTURE_2D : target;
  mipmaps = typeof mipmaps == "undefined" ? true : mipmaps;
  var loadingColors = [[0, 0, 255, 255], [0, 0, 0, 255]];
  var errorColors = [[255, 0, 0, 255], [0, 0, 0, 255]];

  var texture = gl.createTexture();
  gl.bindTexture(target, texture);
  gl.pixelStorei(gl.UNPACK_FLIP_Y_WEBGL, true);
  gl.texImage2D(target, 0, gl.RGBA, gl.RGBA, gl.UNSIGNED_BYTE, pgr.createCheckerboard(loadingColors));
  gl.texParameteri(target, gl.TEXTURE_MIN_FILTER, gl.NEAREST);
  gl.texParameteri(target, gl.TEXTURE_MAG_FILTER, gl.NEAREST);
  gl.bindTexture(target, null);

  var image = new Image;
  image.onerror = function() {
    console.log("error loading texture \"" + url + "\"");
    gl.bindTexture(target, texture);
    gl.texImage2D(target, 0, gl.RGBA, gl.RGBA, gl.UNSIGNED_BYTE, pgr.createCheckerboard(errorColors));
    gl.bindTexture(target, null);
  };
  image.onload = function() {
    gl.bindTexture(target, texture);
    try {
      gl.texImage2D(target, 0, gl.RGBA, gl.RGBA, gl.UNSIGNED_BYTE, image);
    } catch (e) {
      image.onerror();
      return;
    }
    gl.texParameteri(gl.TEXTURE_2D, gl.TEXTURE_MAG_FILTER, gl.LINEAR);
    if(mipmaps) {
      gl.texParameteri(target, gl.TEXTURE_MIN_FILTER, gl.LINEAR_MIPMAP_LINEAR);
      gl.generateMipmap(target);
    } else {
      gl.texParameteri(target, gl.TEXTURE_MIN_FILTER, gl.LINEAR);
    }
    gl.bindTexture(target, null);
  };
  image.src = url;

  return texture;
}

pgr.initialize = function(canvasId) {
  pgr.canvas = document.getElementById(canvasId);
  // google suggests trying those names
  var tryNames = ["webgl", "experimental-webgl", "webkit-3d", "moz-webgl"];
  var gl = null;
  for(var i = 0; i < tryNames.length && gl == null; ++i) {
    try {
      // some implementations throw, some just return null
      gl = pgr.canvas.getContext(tryNames[i]);
    } catch (e) {}
  }
  if(gl == null)
    pgr.log("cannot initialize webgl (get new browser or drivers!)");
  return gl;
};

window.requestAnimFrame = (function() {
  return window.requestAnimationFrame ||
         window.webkitRequestAnimationFrame ||
         window.mozRequestAnimationFrame ||
         window.oRequestAnimationFrame ||
         window.msRequestAnimationFrame ||
         function(/* function FrameRequestCallback */ callback, /* DOMElement Element */ element) {
           window.setTimeout(callback, 1000/60);
         };
})();
