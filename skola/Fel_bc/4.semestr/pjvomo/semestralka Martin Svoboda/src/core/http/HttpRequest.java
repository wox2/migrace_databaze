package core.http;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HttpRequest {
	
	/**
	 * Regularni vyraz zpracovavajici prvni radek pozadavku
	 */
	protected static Pattern statusLinePattern = Pattern.compile("^([A-Z]*) (.*?)([?].*)? (HTTP/1.(0|1))\\s*$");
	
	/**
	 * Vstupni stream
	 */
	protected BufferedReader input;
	
	/**
	 * Byl uz vstupni stream parserovan?
	 */
	protected boolean isParsed = false;
	
	/**
	 * Regularni vyraz zpracovavajici hlavicky pozadavku
	 */
	protected static Pattern headerPattern = Pattern.compile("^([A-Za-z\\-0-9]*): (.*)$");
	
	/**
	 * Uchovava nazev metody z prvni radky pozadavku
	 */
	protected String method;
	
	/**
	 * Uchovava cestu z prvnimu radku pozadavku
	 */
	protected String path;
	
	/**
	 * Uchovava verzi z prvniho radku pozadavku
	 */
	protected String version;
	
	/**
	 * Mapa vsech hlavicek, ktere pozadavek obsahuje
	 */
	protected Map<String, String> headers = new HashMap<String, String>();
	
	public HttpRequest(Reader input) {
		this.input = new BufferedReader(input);		
	}
	
	/**
	 * Parsruje vstupni proud
	 * @throws IOException
	 * @throws InvalidRequestException
	 * @throws EmptyRequestException
	 */
	public void parseInput() throws IOException, InvalidRequestException, EmptyRequestException {
		
		if (!isParsed) {
			isParsed = true;
			String line;
			int i = 0;
			while ((line = input.readLine()) != null) {
				// prazdny radek = konec hlavicky
				if (line.length() == 0) {
					break;
				}
				
				if (i == 0) {
					Matcher matcher = statusLinePattern.matcher(line);
					if (matcher.find()) {
						
						if (matcher.group(2).trim().length() == 0) {
							throw new InvalidRequestException();
						}
						
						method = matcher.group(1);
						path = matcher.group(2).trim();
						version = matcher.group(4);
					} else {
						throw new InvalidRequestException();
					}
				} else {
					Matcher matcher = headerPattern.matcher(line);
					if (matcher.find()) {
						headers.put(matcher.group(1), matcher.group(2));
					} else {
						throw new InvalidRequestException();
					}
				}
				i++;
			}
			
			if (i == 0) {
				throw new EmptyRequestException();
			}
		}
	}
	
	/**
	 * Vraci cestu metodu udanou v prvnim radku requestu
	 * @return
	 */
	public String getMethod() {
		if (!isParsed) {
			throw new HttpRequestIsNotParsedException();
		}
		
		return this.method;
	}
	
	/**
	 * Vraci cestu udanou v prvnim radku requestu
	 * @return
	 */
	public String getPath() {
		if (!isParsed) {
			throw new HttpRequestIsNotParsedException();
		}
		
		return this.path;
	}
	
	/**
	 * Vraci verzi http protokolu
	 * @return
	 */
	public String getVersion() {
		if (!isParsed) {
			throw new HttpRequestIsNotParsedException();
		}
		
		return this.version;
	}
	
	/**
	 * Vraci obsah hlavicky podle jejiho nazvu
	 * @param name
	 * @return
	 * @throws HeaderNotExistException
	 */
	public String getHeader(String name) throws HeaderNotExistException {
		if (!isParsed) {
			throw new HttpRequestIsNotParsedException();
		}
		
		if (headers.containsKey(name)) {
			return headers.get(name);
		} else {
			throw new HeaderNotExistException();
		}
	}
}