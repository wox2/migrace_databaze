/* package */
package cz.XmlTester;

/* imports */
import javax.xml.parsers.*;
import org.xml.sax.*;
import org.xml.sax.helpers.*;
import org.w3c.dom.*;
import org.apache.xerces.dom.*;

import java.lang.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;

/******************************************************************************/
/** Class for transforming XML schema to object-relational schema */
class AddData extends Object
   {
   /** constants */
   public static final int VARCHAR   = 1;  /** basic simple type is VARCHAR(2) */
   public static final int DECIMAL   = 2;  /** basic simple type is DECIMAL */
   public static final int FLOAT     = 3;  /** basic simple type is FLOAT */
   public static final int DOUBLE    = 4;  /** basic simple type is DOUBLE */
   public static final int INTERVAL  = 5;  /** basic simple type is INTERVAL */
   public static final int TIMESTAMP = 6;  /** basic simple type is TIMESTAMP */
   public static final int DATE      = 7;  /** basic simple type is DATE */
   public static final int BLOB      = 8;  /** basic simple type is BLOB */

   public static final char no   = 'o';    /** no simple type */
   public static final char num  = 'n';    /** basic simple type is number type */
   public static final char str  = 's';    /** basic simple type is string type */
   public static final char numA = 'N';    /** basic simple type is array of numbers */
   public static final char strA = 'S';    /** basic simple type is array of strings */
   public static final char comp = 'C';    /** basic complex type */

   public static final char ref  = 'R';    /** mapped to reference */
   public static final char col  = 'C';    /** mapped to collumn */
   public static final char refA = 'A';    /** mapped to array of references */

   public static final char el = 'E';      /** element type element */
   public static final char al = 'A';      /** element type all */
   public static final char ch = 'C';      /** element type choice */
   public static final char se = 'S';      /** element type sequence */

   public static final int maxStr  = 200;  /** max. length of VARCHAR and BLOB */
   public static final int maxArr  =  50;  /** max. length of VARRAY */
   public static final int unbound = 100;  /** unbounded maxOccurs */

   /** auxiliary variables */
   private int ID;                         /** ID of this node */
   private Node globalNode;                /** edge to global defined element */
   private boolean proc;                   /** was the node already processed? */
   private int tableCreated;              /** was the table already created? */

   /** variables for DOM tree */
   // restrictions for simple type
   private String minExclusive;                    /** value of minExclusive facet */
   private String maxExclusive;                    /** value of maxExclusive facet */
   private String minInclusive;                    /** value of minInclusive facet */
   private String maxInclusive;                    /** value of maxInclusive facet */
   private Integer totalDigits;                    /** value of totalDigits facet */
   private Integer fractionDigits;                 /** value of fractionDigits facet */
   private Integer length;                         /** value of length facet */
   private Integer maxLength;                      /** value of maxLength facet */
   private Integer minLength;                      /** value of minLength facet */
   private ArrayList enumeration;                  /** value of enumeration facet */

   // type of simple type
   private int simpleType;                         /** ID of basic simple type */
   private boolean arrayOfSimpleType;              /** true == array of basic simple types */

   // parameters for attribute node
   private String fixed;                           /** fixed value */
   private boolean use;                            /** true == required use */

   // parameters for complex type
   private char mapType;                           /** map type of node */
   private int order;                              /** order of this node */
   private int attrID;                             /** attribute ID for attributes */
   private ArrayList attrList;                     /** list of attributes' AddData for complexTypes */
   private int elemID;                             /** element ID for elements */
   private ArrayList elemList;                     /** list of elements' AddData for complexTypes */
   private String udtName;                         /** name od UDT for complex types */
   private int udtID;                              /** ID od UDT for complex types */
   private char elemType;                          /** element type of node */

   // parameters for elements
   private Integer minOccurs;                      /** min. amount of occurences */
   private Integer maxOccurs;                      /** max. amount of occurences */
   private boolean global;                         /** true == globally defined element */
   private boolean almGlobal;                      /** true == almost globally defined element */
   private boolean createTable;                    /** true == create typed table */
   private Node myNode;                            /** pointer to owner of the data */
   private boolean special;                        /** is it a special node? */

   /***************************************************************************/
   /** Copies content of specified AddData to current. */
   public void copyContent
      (
      AddData ad      /** copied AddData */
      )
      {
      // restrictions for simple type
      minExclusive   = ad.getMinExclusive();
      maxExclusive   = ad.getMaxExclusive();
      minInclusive   = ad.getMinInclusive();
      maxInclusive   = ad.getMaxInclusive();
      totalDigits    = ad.getTotalDigits();
      fractionDigits = ad.getFractionDigits();
      length         = ad.getLength();
      maxLength      = ad.getMaxLength();
      minLength      = ad.getMinLength();
      ArrayList al   = ad.getEnumeration();
      enumeration.clear();
      for (int i=0; i<al.size(); i++)
         {
         enumeration.add(al.get(i));
         } /* for */

      // type of simple type
      simpleType        = ad.getSimpleType();
      arrayOfSimpleType = ad.getArrayOfSimpleType();

      // parameters for attribute node
      fixed = ad.getFixed();
      use   = ad.getUse();

      // attribute(s) IDs
      attrID = ad.getAttrID();
      al     = ad.getAttrList();
      attrList.clear();
      for (int i=0; i<al.size(); i++)
         {
         AddData a = (AddData)al.get(i);
         if (a.isGlobal())
            {
            attrList.add(new AddData(a));
            }
         else
            {
            attrList.add(a);
            } /* else */
         } /* for */

      // order
      order = ad.getOrder();

      // parameters for complex type
      udtName = ad.getUdtName();
      udtID   = ad.getUdtID();

      // element(s) IDs
      elemID = ad.getElemID();
      al     = ad.getElemList();
      elemList.clear();
      for (int i=0; i<al.size(); i++)
         {
         AddData a = (AddData)al.get(i);
         if (a.isGlobal())
            {
            elemList.add(new AddData(a));
            }
         else
            {
            elemList.add(a);
            } /* else */
         } /* for */

      // map type
      mapType  = ad.getMapType();
      elemType = ad.getElemType();

      // occurences
      minOccurs = ad.getMinOccurs();
      maxOccurs = ad.getMaxOccurs();
      global    = ad.isGlobal();
      almGlobal = ad.isAlmGlobal();
      myNode    = ad.getMyNode();
      special   = ad.isSpecial();
      } /* copyContent */

   /***************************************************************************/
   /** Clears all restriction values */
   private void clearAllRestr ()
      {
      // restrictions for simple type
      minExclusive   = null;
      maxExclusive   = null;
      minInclusive   = null;
      maxInclusive   = null;
      totalDigits    = null;
      fractionDigits = null;
      length         = null;
      maxLength      = null;
      minLength      = null;
      enumeration.clear();
      } /* clearAllRestr */

   /***************************************************************************/
   /** Clears all values */
   private void clearAll ()
      {
      // restrictions for simple type
      clearAllRestr();

      // type of simple type
      simpleType        = 0;
      arrayOfSimpleType = false;

      // parameters for attribute node
      fixed = null;
      use   = false;

      // attribute(s) IDs
      attrID = -1;
      attrList.clear();

      // order
      order = 0;

      // parameters for complex type
      udtName = null;
      udtID   = -1;

      // element(s) IDs
      elemID = -1;
      elemList.clear();

      // map type
      mapType  = no;
      elemType = ' ';

      // occurences
      minOccurs = new Integer("1");
      maxOccurs = new Integer("1");
      global      = false;
      almGlobal   = false;
      createTable = false;
      myNode      = null;
      special     = false;
      } /* clearAll */

   /***************************************************************************/
   /** Constructor */
   public AddData
      (
      AddData ad   /** AddData */
      )
      {
      ID = ad.getID();
      globalNode = ad.getGlobalNode();
      setProcessed(ad.isProcessed());

      enumeration = new ArrayList();
      attrList    = new ArrayList();
      elemList    = new ArrayList();
      copyContent(ad);
      } /* AddData  */

   /***************************************************************************/
   /** Constructor */
   public AddData
      (
      int _ID,               /** new ID */
      Node _globalNode       /** new edge */
      )
      {
      // auxiliary variables
      ID           = _ID;
      globalNode   = _globalNode;
      proc         = false;
      tableCreated = 0;

      // variables for DOM tree
      enumeration = new ArrayList();
      attrList    = new ArrayList();
      elemList    = new ArrayList();
      clearAll();
      } /* AddData */

   /***************************************************************************/
   /** Constructor */
   public AddData
      (
      int _ID                /** new ID */
      )
      {
      // auxiliary variables
      ID           = _ID;
      globalNode   = null;
      proc         = false;
      tableCreated = 0;

      // variables for DOM tree
      enumeration = new ArrayList();
      attrList    = new ArrayList();
      elemList    = new ArrayList();
      clearAll();
      } /* AddData */

   /***************************************************************************/
   /** Returns ID. */
   public int getID ()
      {
      return ID;
      } /* getID */

   /***************************************************************************/
   /** Sets globalNode object. */
   public void setGlobalNode
      (
      Node _globalNode   /** new edge */
      )
      {
      globalNode = _globalNode;
      } /* setGlobalNode */

   /***************************************************************************/
   /** Returns ID. */
   public Node getGlobalNode ()
      {
      return globalNode;
      } /* getGlobalNode */

   /***************************************************************************/
   /** Was the node processed? */
   public boolean isProcessed ()
      {
      return proc;
      } /* isProcessed */

   /***************************************************************************/
   /** Set whether the node was processed */
   public void setProcessed
      (
      boolean _proc   /** new value */
      )
      {
      proc = _proc;
      } /* setProcessed */

   /***************************************************************************/
   /** Was the table created? */
   public int isCreated ()
      {
      return tableCreated;
      } /* isCreated */

   /***************************************************************************/
  /** Set whether the table was created */
   public void setCreated
      (
      int val   /** new value */
      )
      {
      tableCreated = val;
      } /* setCreated */

   /***************************************************************************/
   /** Sets the minExclusive variable. */
   public void setMinExclusive
      (
      String new_val      /** new value */
      )
      {
      minExclusive = new_val;
      } /* setMinExclusive */

   /***************************************************************************/
   /** Gets the minExclusive variable. */
   public String getMinExclusive ()
      {
      return minExclusive;
      } /* getMinExclusive */

   /***************************************************************************/
   /** Sets the minInclusive variable. */
   public void setMinInclusive
      (
      String new_val      /** new value */
      )
      {
      minInclusive = new_val;
      } /* setMinInclusive */

   /***************************************************************************/
   /** Gets the minInclusive variable. */
   public String getMinInclusive ()
      {
      return minInclusive;
      } /* getMinInclusive */

   /***************************************************************************/
   /** Sets the maxExclusive variable. */
   public void setMaxExclusive
      (
      String new_val      /** new value */
      )
      {
      maxExclusive = new_val;
      } /* setMaxExclusive */

   /***************************************************************************/
   /** Gets the maxExclusive variable. */
   public String getMaxExclusive ()
      {
      return maxExclusive;
      } /* getMaxExclusive */

   /***************************************************************************/
   /** Sets the maxInclusive variable. */
   public void setMaxInclusive
      (
      String new_val      /** new value */
      )
      {
      maxInclusive = new_val;
      } /* setMaxInclusive */

   /***************************************************************************/
   /** Gets the maxInclusive variable. */
   public String getMaxInclusive ()
      {
      return maxInclusive;
      } /* getMaxInclusive */

   /***************************************************************************/
   /** Sets the totalDigits variable. */
   public void setTotalDigits
      (
      String new_val      /** new value */
      )
      {
      totalDigits = new Integer(new_val);
      } /* setTotalDigits */

   /***************************************************************************/
   /** Gets the totalDigits variable. */
   public Integer getTotalDigits ()
      {
      return totalDigits;
      } /* getTotalDigits */

   /***************************************************************************/
   /** Sets the fractionDigits variable. */
   public void setFractionDigits
      (
      String new_val      /** new value */
      )
      {
      fractionDigits = new Integer(new_val);
      } /* setFractionDigits */

   /***************************************************************************/
   /** Gets the fractionDigits variable. */
   public Integer getFractionDigits ()
      {
      return fractionDigits;
      } /* getFractionDigits */

   /***************************************************************************/
   /** Sets the length variable. */
   public void setLength
      (
      String new_val      /** new value */
      )
      {
      length = new Integer(new_val);
      } /* setLength */

   /***************************************************************************/
   /** Gets the length variable. */
   public Integer getLength ()
      {
      return length;
      } /* getLength */

   /***************************************************************************/
   /** Sets the minLength variable. */
   public void setMinLength
      (
      String new_val      /** new value */
      )
      {
      minLength = new Integer(new_val);
      } /* setMinLength */

   /***************************************************************************/
   /** Gets the minLength variable. */
   public Integer getMinLength ()
      {
      return minLength;
      } /* getMinLength */

   /***************************************************************************/
   /** Sets the maxLength variable. */
   public void setMaxLength
      (
      String new_val      /** new value */
      )
      {
      maxLength = new Integer(new_val);
      } /* setMaxLength */

   /***************************************************************************/
   /** Gets the maxLength variable. */
   public Integer getMaxLength ()
      {
      return maxLength;
      } /* getMaxLength */

   /***************************************************************************/
   /** Sets the enumeration variable. */
   public void setEnumeration
      (
      String new_val      /** new value */
      )
      {
      enumeration.add((Object)new_val);
      } /* setEnumeration */

   /***************************************************************************/
   /** Gets the maxLength variable. */
   public ArrayList getEnumeration ()
      {
      return enumeration;
      } /* getEnumeration */

   /***************************************************************************/
   /** Sets the basic simple type konstant. */
   public void setSimpleType
      (
      String xmlType,      /** name of xml type */
      String prefix        /** prefix for namespace */
      )
      {
      if (xmlType.equals(prefix + "string") ||
          xmlType.equals(prefix + "normalizedString") ||
          xmlType.equals(prefix + "token") ||
          xmlType.equals(prefix + "language") ||
          xmlType.equals(prefix + "NMTOKEN") ||
          xmlType.equals(prefix + "Name") ||
          xmlType.equals(prefix + "NCName") ||
          xmlType.equals(prefix + "ENTITY") ||
          xmlType.equals(prefix + "ID") ||
          xmlType.equals(prefix + "IDREF") ||
          xmlType.equals(prefix + "boolean") ||       // type BOOLEAN is not supported in Oracle9i
          xmlType.equals(prefix + "time") ||          // type TIME is not supported in Oracle9i
          xmlType.equals(prefix + "gYearMonth") ||
          xmlType.equals(prefix + "gYear") ||
          xmlType.equals(prefix + "gMonthDay") ||
          xmlType.equals(prefix + "gMonth") ||
          xmlType.equals(prefix + "gDay") ||
          xmlType.equals(prefix + "hexBinary") ||     // predicate SIMILAR TO is not supported in Oracle9i
          xmlType.equals(prefix + "anyURI") ||
          xmlType.equals(prefix + "QName") ||
          xmlType.equals(prefix + "NOTATION") ||
          xmlType.equals(prefix + "anyType"))
         {
         simpleType = VARCHAR;
         }
      else if (xmlType.equals(prefix + "NMTOKENS") ||
               xmlType.equals(prefix + "ENTITIES") ||
               xmlType.equals(prefix + "IDREFS"))
         {
         simpleType        = VARCHAR;
         arrayOfSimpleType = true;
         }
      else if (xmlType.equals(prefix + "decimal"))
         {
         simpleType = DECIMAL;
         }
      else if (xmlType.equals(prefix + "integer"))
         {
         simpleType     = DECIMAL;
         fractionDigits = new Integer("0");
         }
      else if (xmlType.equals(prefix + "positiveInteger"))
         {
         simpleType     = DECIMAL;
         fractionDigits = new Integer("0");
         minExclusive   = new String("0");
         }
      else if (xmlType.equals(prefix + "negativeInteger"))
         {
         simpleType     = DECIMAL;
         fractionDigits = new Integer("0");
         maxExclusive   = new String("0");
         }
      else if (xmlType.equals(prefix + "nonPositiveInteger"))
         {
         simpleType     = DECIMAL;
         fractionDigits = new Integer("0");
         maxInclusive   = new String("0");
         }
      else if (xmlType.equals(prefix + "nonNegativeInteger"))
         {
         simpleType     = DECIMAL;
         fractionDigits = new Integer("0");
         minInclusive   = new String("0");
         }
      else if (xmlType.equals(prefix + "long"))
         {
         simpleType     = DECIMAL;
         fractionDigits = new Integer("0");
         minInclusive   = new String("-9223372036854775808");
         maxExclusive   = new String("9223372036854775808");
         }
      else if (xmlType.equals(prefix + "int"))
         {
         simpleType     = DECIMAL;
         fractionDigits = new Integer("0");
         minInclusive   = new String("-2147483648");
         maxExclusive   = new String("2147483648");
         }
      else if (xmlType.equals(prefix + "short"))
         {
         simpleType     = DECIMAL;
         fractionDigits = new Integer("0");
         minInclusive   = new String("-32768");
         maxExclusive   = new String("32768");
         }
      else if (xmlType.equals(prefix + "byte"))
         {
         simpleType     = DECIMAL;
         fractionDigits = new Integer("0");
         minInclusive   = new String("-128");
         maxExclusive   = new String("128");
         }
      else if (xmlType.equals(prefix + "unsignedLong"))
         {
         simpleType     = DECIMAL;
         fractionDigits = new Integer("0");
         minInclusive   = new String("0");
         maxExclusive   = new String("18446744073709551616");
         }
      else if (xmlType.equals(prefix + "unsignedInt"))
         {
         simpleType     = DECIMAL;
         fractionDigits = new Integer("0");
         minInclusive   = new String("0");
         maxExclusive   = new String("4294967296");
         }
      else if (xmlType.equals(prefix + "unsignedShort"))
         {
         simpleType     = DECIMAL;
         fractionDigits = new Integer("0");
         minInclusive   = new String("0");
         maxExclusive   = new String("65536");
         }
      else if (xmlType.equals(prefix + "unsignedByte"))
         {
         simpleType     = DECIMAL;
         fractionDigits = new Integer("0");
         minInclusive   = new String("0");
         maxExclusive   = new String("256");
         }
      else if (xmlType.equals(prefix + "float"))
         {
         simpleType = FLOAT;
         }
      else if (xmlType.equals(prefix + "double"))
         {
         simpleType = DOUBLE;
         }
      else if (xmlType.equals(prefix + "duration"))
         {
         simpleType = INTERVAL;
         }
      else if (xmlType.equals(prefix + "dateTime"))
         {
         simpleType = TIMESTAMP;
         }
      else if (xmlType.equals(prefix + "date"))
         {
         simpleType = DATE;
         }
      else if (xmlType.equals(prefix + "base64Binary"))
         {
         simpleType = BLOB;
         } /* if */
      setSimpleMapType();
      } /* setSimpleType */

   /***************************************************************************/
   /** Gets the simpleType variable. */
   public int getSimpleType ()
      {
      return simpleType;
      } /* getSimpleType */

   /***************************************************************************/
   /** Gets the arrayOfSimpleType variable. */
   public boolean getArrayOfSimpleType ()
      {
      return arrayOfSimpleType;
      } /* getArrayOfSimpleType */

   /***************************************************************************/
   /** Sets properties to list of simple types. */
   public void setList ()
      {
      // clear already set facets
      clearAllRestr();

      // change other variables
      arrayOfSimpleType = true;
      setSimpleMapType();
      } /* setList */

   /***************************************************************************/
   /** Sets properties to union of simple types. */
   public void setUnion ()
      {
      // clear already set facets
      clearAllRestr();

      // change other variables
      simpleType        = VARCHAR;
      arrayOfSimpleType = false;
      setSimpleMapType();
      } /* setUnion */

   /***************************************************************************/
   /** Sets fixed value. */
   public void setFixed
      (
      String val   /** value of corresponding attribute */
      )
      {
      // value was not set
      if (val == null)
         {
         return;
         } /* if */

      // change array to VARCHAR
      if (arrayOfSimpleType)
         {
         clearAllRestr();
         simpleType        = VARCHAR;
         arrayOfSimpleType = false;
         setSimpleMapType();
         } /* if */

      fixed = new String(val);
      } /* setFixed */

   /***************************************************************************/
   /** Gets the fixed variable. */
   public String getFixed ()
      {
      return fixed;
      } /* getFixed */

   /***************************************************************************/
   /** Sets use constraint. */
   public void setUse
      (
      String val   /** value of corresponding attribute */
      )
      {
      // value was not set
      if (val == null)
         {
         return;
         } /* if */

      if ((val.equals("required")) || (val.equals("false")))
         {
         use = true;
         } /* if */
      } /* setUse */

   /***************************************************************************/
   /** Gets the use variable. */
   public boolean getUse ()
      {
      return use;
      } /* getUse */

   /***************************************************************************/
   /** Sets map type for simple type. */
   public void setSimpleMapType ()
      {
      switch (simpleType)
         {
         case VARCHAR   :
         case INTERVAL  :
         case TIMESTAMP :
         case DATE      :
         case BLOB      : if (arrayOfSimpleType)
                             {
                             mapType = strA;
                             }
                          else
                             {
                             mapType = str;
                             } /* else */
                          break;
         case DECIMAL   :
         case FLOAT     :
         case DOUBLE    : if (arrayOfSimpleType)
                             {
                             mapType = numA;
                             }
                          else
                             {
                             mapType = num;
                             } /* else */
                          break;
         default        : mapType = no;
         } /* switch */
      } /* setSimpleMapType */

   /***************************************************************************/
   /** Sets map type for complex type. */
   public void setComplexMapType ()
      {
      mapType = comp;
      } /* setComplexMapType */

   /***************************************************************************/
   /** Returns map type of simple type. */
   public char getMapType ()
      {
      return mapType;
      } /* getMapType */

   /***************************************************************************/
   /** Returns element type. */
   public char getElemType ()
      {
      return elemType;
      } /* getElemType */

   /***************************************************************************/
   /** Sets element type. */
   public void setElemType
      (
      char val
      )
      {
      elemType = val;
      } /* setElemType */

   /***************************************************************************/
   /** Stores attrID. */
   public void setAttrID
      (
      int _attrID    /** new value */
      )
      {
      attrID = _attrID;
      } /* setAttrID */

   /***************************************************************************/
   /** Returns attrID. */
   public int getAttrID ()
      {
      return attrID;
      } /* getAttrID */

   /***************************************************************************/
   /** Returns attrList. */
   public ArrayList getAttrList ()
      {
      return attrList;
      } /* getAttrList */

   /***************************************************************************/
   /** Returns attrList size. */
   public int getAttrListSize ()
      {
      return attrList.size();
      } /* getAttrListSize */

   /***************************************************************************/
   /** Adds new attribute's AddData to attrList. */
   public void addAttrList
      (
      AddData attr      /** new attribute */
      )
      {
      if (attr == null)
         {
         return;
         } /* if */

      if (attr.isGlobal())
         {
         attrList.add(new AddData(attr));
         }
      else
         {
         attrList.add(attr);
         } /* else */
      } /* addAttrList */

   /***************************************************************************/
   /** Adds new attribute's AddData from the list to attrList. */
   public void addAttrList
      (
      ArrayList list    /** list of new IDs */
      )
      {
      for (int i=0; i<list.size();i++)
         {
         AddData a = (AddData)list.get(i);
         if (a.isGlobal())
            {
            attrList.add(new AddData(a));
            }
         else
            {
            attrList.add(a);
            } /* else */
         } /* for */
      } /* addAttrList */

   /***************************************************************************/
   /** Returns description of simple type. */
   public String getSimpleTypeDesc
      (
      String udtN,     /** name of complex type */
      String pref      /** prefix of attribute */
      )
      {
      String type = null;
      String comm = "";

      // crate proper type
      switch (simpleType)
         {
         case DATE      : type = "DATE";
                          break;
         case FLOAT     : type = "FLOAT";
                          break;
         case DOUBLE    : type = "DOUBLE PRECISION";
                          break;
         case TIMESTAMP : type = "VARCHAR2(50)";       // TIMESTAMP WITH TIME ZONE
                          break;
         case INTERVAL  : type = "VARCHAR2(50)";       // 2x INTERVAL (udtInterval)
                          break;
         case VARCHAR   : if (!arrayOfSimpleType && (maxLength != null))
                             {
                             type = "VARCHAR2(" + maxLength + ")";
                             }
                          else
                             {
                             type = "VARCHAR2(" + maxStr + ")";
                             } /* else */
                          break;
         case BLOB      : type = "BLOB";
                          break;
         case DECIMAL   : Integer td = new Integer(38);
                          Integer fd = new Integer(19);
                          if (totalDigits != null)
                             {
                             td = totalDigits;
                             } /* if */
                          if (fractionDigits != null)
                             {
                             fd = fractionDigits;
                             } /* if */
                          type = "DECIMAL(" + td + "," + fd + ")";
                          break;
         } /* switch */

      if (arrayOfSimpleType)
         {
         // name of the varray udt
         String udt  = udtN + "_" + order;

         // command for creating varray
         if (maxLength != null)
            {
            comm = "CREATE TYPE " + udt + " AS VARRAY(" + maxLength + ") OF " + type;
            }
         else if (length != null)
            {
            comm = "CREATE TYPE " + udt + " AS VARRAY(" + length + ") OF " + type;
            }
         else
            {
            comm = "CREATE TYPE " + udt + " AS VARRAY(" + maxArr + ") OF " + type;
            } /* else */

         type = udt;
         } /* if */

      return (comm + pref + order + " " + type);
      } /* getSimpleTypeDesc */

   /***************************************************************************/
   /** Returns constraints of simple type. */
   public String getSimpleTypeConstr
      (
      String pref     /** prefix of attribute */
      )
      {
      String name = pref + order;
      String constr = "";

      // constraint use
      if (use == true)
         {
         constr = constr + "CHECK (" + name + " IS NOT NULL), ";
         } /* if */
      if (arrayOfSimpleType)   // ARRAY can't have any other constraint
         {
         if (!constr.equals(""))
            {
            constr = constr.substring(0,constr.length()-2);
            } /* if */
         return constr;
         } /* if */

      // constraint fixed
      if (fixed != null)
         {
         if (mapType == 's')   // string
            {
            constr = constr + "CHECK (" + name + " = '" + fixed + "'), ";
            }
         else                  // number
            {
            constr = constr + "CHECK (" + name + " = " + fixed + "), ";
            } /* else */
         } /* if */

      // constraint length
      if (length != null)
         {
         constr = constr + "CHECK (LENGTH(" + name + ") = " + length + "), ";
         } /* if */

      // constraint minLength
      if (minLength != null)
         {
         constr = constr + "CHECK (LENGTH(" + name + ") >= " + minLength + "), ";
         } /* if */

      // constraint minExclusive
      if (minExclusive != null)
         {
         if (mapType == 's')   // string
            {
            constr = constr + "CHECK (" + name + " > '" + minExclusive + "'), ";
            }
         else                          // number
            {
            constr = constr + "CHECK (" + name + " > " + minExclusive + "), ";
            } /* else */
         } /* if */

      // constraint maxExclusive
      if (maxExclusive != null)
         {
         if (mapType == 's')   // string
            {
            constr = constr + "CHECK (" + name + " < '" + maxExclusive + "'), ";
            }
         else                          // number
            {
            constr = constr + "CHECK (" + name + " < " +  maxExclusive + "), ";
            } /* else */
         } /* if */

      // constraint minInclusive
      if (minInclusive != null)
         {
         if (mapType == 's')   // string
            {
            constr = constr + "CHECK (" + name + " >= '" + minInclusive + "'), ";
            }
         else                          // number
            {
            constr = constr + "CHECK (" + name + " >= " + minInclusive + "), ";
            } /* else */
         } /* if */

      // constraint maxInclusive
      if (maxInclusive != null)
         {
         if (mapType == 's')   // string
            {
            constr = constr + "CHECK (" + name + " <= '" + maxInclusive + "'), ";
            }
         else                          // number
            {
            constr = constr + "CHECK (" + name + " <= " + maxInclusive + "), ";
            } /* else */
         } /* if */

      // constraint enumeration
      ArrayList al = enumeration;
      if (al.size() > 0)
         {
         constr = constr + "CHECK (" + name + " IN (";
         for (int j=0; j<al.size(); j++)
            {
            String en = (String)al.get(j);
            if (mapType == 's')   // string
               {
               constr = constr + "'" + en + "', ";
               }
            else                          // number
               {
               constr = constr + en + ", ";
               } /* else */
            } /* for */
         constr = constr.substring(0,constr.length()-2) + ")), ";
         } /* if */

      // add name, if any constraint was set
      if (!constr.equals(""))
         {
         constr = constr.substring(0,constr.length()-2);
         } /* if */
      return constr;
      } /* getSimpleTypeConstr */

   /***************************************************************************/
   /** Returns description of complex type. */
   public String getComplexTypeDesc
      (
      String udtN,    /** name of complex type */
      String pref     /** prefix of attribute */
      )
      {
      String type = null;
      String comm = "";

      if (maxOccurs.intValue() == 1)
         {
         if (global || almGlobal)     // globally defined element
            {
            type = "REF " + udtName;
            }
         else                         // locally defined type
            {
            type = udtName;
            } /* else */
         }
      else
         {
         type = udtN + "_" + order;
         comm = "CREATE TYPE " + type + " AS VARRAY(" + maxOccurs + ") OF REF " + udtName;

         if (!global)
            {
            createTable = true;
            } /* if */
         } /* else */

      return (comm + pref + order + " " + type);
      } /* getComplexTypeDesc */

   /***************************************************************************/
   /** Returns constraints of complex type. */
   public String getComplexTypeConstr
      (
      String pref     /** prefix of attribute */
      )
      {
      String name = pref + order;
      String constr = "";

      // SCOPE for references
      if ((maxOccurs.intValue() == 1) && (global || almGlobal))
         {
         constr = constr + "SCOPE FOR (" + name + ") IS T_" + udtName + ", ";
         } /* if */

      // NOT NULL for not reference types
      if ( ((minOccurs.intValue() == 1) && (!global && !almGlobal)) ||     // not reference
            (minOccurs.intValue() > 1))                                    // array
         {
         constr = constr + "CHECK (" + name + " IS NOT NULL), ";
         } /* if */

      // add name, if any constraint was set
      if (!constr.equals(""))
         {
         constr = constr.substring(0,constr.length()-2);
         } /* if */
      return constr;
      } /* getComplexTypeConstr */

   /***************************************************************************/
   /** Sets udtName. */
   public void setUdtName
      (
      String _udtName,    /** name of udt */
      int _udtID          /** ID of UDT */
      )
      {
      udtName = _udtName;
      udtID = _udtID;
      } /* setUdtName */

   /***************************************************************************/
   /** Returns udtName. */
   public String getUdtName ()
      {
      return udtName;
      } /* getUdtName */

   /***************************************************************************/
   /** Returns udtID. */
   public int getUdtID ()
      {
      return udtID;
      } /* getUdtID */

   /***************************************************************************/
   /** Sets order. */
   public void setOrder
      (
      int _order   /** new order value */
      )
      {
      order = _order;
      } /* setOrder */

   /***************************************************************************/
   /** Returns order. */
   public int getOrder ()
      {
      return order;
      } /* getOrder */

   /***************************************************************************/
   /** Stores elemID. */
   public void setElemID
      (
      int _elemID    /** new value */
      )
      {
      elemID = _elemID;
      } /* setElemID */

   /***************************************************************************/
   /** Returns elemID. */
   public int getElemID ()
      {
      return elemID;
      } /* getElemID */

   /***************************************************************************/
   /** Returns elemList. */
   public ArrayList getElemList ()
      {
      return elemList;
      } /* getElemList */

   /***************************************************************************/
   /** Returns elemList size. */
   public int getElemListSize ()
      {
      return elemList.size();
      } /* getElemListSize */

   /***************************************************************************/
   /** Adds new element's AddData to elemList. */
   public void addElemList
      (
      AddData el      /** new attribute */
      )
      {
      if (el == null)
         {
         return;
         } /* if */
      if (el.isGlobal())
         {
         elemList.add(new AddData(el));
         }
      else
         {
         elemList.add(el);
         } /* else */
      } /* addElemList */

   /***************************************************************************/
   /** Adds new element's AddData from the list to elemList. */
   public void addElemList
      (
      ArrayList list    /** list of new IDs */
      )
      {
      for (int i=0; i<list.size();i++)
         {
         AddData el = (AddData)list.get(i);
         if (el.isGlobal())
            {
            elemList.add(new AddData(el));
            }
         else
            {
            elemList.add(el);
            } /* else */
         } /* for */
      } /* addElemList */

   /***************************************************************************/
   /** Stores minOccurs. */
   public void setMinOccurs
      (
      String val,   /** new value */
      boolean mul   /** true == multiple values */
      )
      {
      if (val == null)
         {
         return;
         } /* if */

      if (mul)
         {
         Integer n = new Integer(val);
         minOccurs = new Integer(minOccurs.intValue() * n.intValue());
         }
      else
         {
         minOccurs = new Integer(val);
         } /* else */
      } /* setMinOccurs */

   /***************************************************************************/
   /** Returns minOccurs. */
   public Integer getMinOccurs ()
      {
      return minOccurs;
      } /* getMinOccurs */

   /***************************************************************************/
   /** Stores maxOccurs. */
   public void setMaxOccurs
      (
      String val,   /** new value */
      boolean mul   /** true == multiple values */
      )
      {
      if (val == null)
         {
         return;
         } /* if */

      if (mul)
         {
         Integer n = new Integer(val);
         maxOccurs = new Integer(maxOccurs.intValue() * n.intValue());

         if (maxOccurs.intValue() > unbound)
            {
            maxOccurs = new Integer(unbound);
            } /* if */
         }
      else
         {
         if (val.equals("unbounded"))
            {
            maxOccurs = new Integer(unbound);
            }
         else
            {
            maxOccurs = new Integer(val);
            } /* else */
         } /* else */
      } /* setMaxOccurs */

   /***************************************************************************/
   /** Returns maxOccurs. */
   public Integer getMaxOccurs ()
      {
      return maxOccurs;
      } /* getMaxOccurs */

   /***************************************************************************/
   /** Sets global variable. */
   public void setGlobal
      (
      boolean _global   /** new value */
      )
      {
      global = _global;
      } /* setGlobal */

   /***************************************************************************/
   /** Returns global variable. */
   public boolean isGlobal ()
      {
      return global;
      } /* isGlobal */

   /***************************************************************************/
   /** Sets almGlobal variable. */
   public void setAlmGlobal
      (
      boolean _almGlobal   /** new value */
      )
      {
      almGlobal   = _almGlobal;
      createTable = almGlobal;
      } /* setAlmGlobal */

   /***************************************************************************/
   /** Returns almGlobal variable. */
   public boolean isAlmGlobal ()
      {
      return almGlobal;
      } /* isAlmGlobal */

   /***************************************************************************/
   /** Sets myNode variable. */
   public void setMyNode
      (
      Node _myNode   /** new value */
      )
      {
      myNode = _myNode;
      } /* setMyNode */

   /***************************************************************************/
   /** Returns myNode variable. */
   public Node getMyNode ()
      {
      return myNode;
      } /* getMyNode */

   /***************************************************************************/
   /** Sets special variable. */
   public void setSpecial
      (
      boolean _special   /** new value */
      )
      {
      special = _special;
      } /* setSpecial */

   /***************************************************************************/
   /** Returns myNode variable. */
   public boolean isSpecial ()
      {
      return special;
      } /* isSpecial */

   /***************************************************************************/
   /** Sets createTable variable. */
   public void setCreateTable
      (
      boolean _createTable   /** new value */
      )
      {
      createTable = _createTable;
      } /* setCreateTable */

   /***************************************************************************/
   /** Returns createTable variable. */
   public boolean getCreateTable ()
      {
      return createTable;
      } /* getCreateTable */

   /***************************************************************************/
   /** Returns description of complex type. */
   public char getColumnMap ()
      {
      if (maxOccurs.intValue() == 1)
         {
         if (global || almGlobal)         // reference
            {
            return ref;
            }
         else                             // column
            {
            return col;
            } /* else */
         }
      else                                // array of references
         {
         return refA;
         } /* else */
      } /* getColumnMap */
   } /* AddData */

/******************************************************************************/
/** Class for transforming XML schema to object-relational schema */
public class XMLSchema2DB
   {
   private final String scrName = "./tmp/listingXMLSchema.txt";   /** name of the script */
   private String errMes;                                         /** error message */

   /** constants */
   private static final int UN   = 0;  /** uninteresting parent */
   private static final int ST   = 1;  /** simpleType parent */
   private static final int SC   = 2;  /** simpleContent parent */
   private static final int CC   = 3;  /** complexContent parent */
   private static final int SPEC = 4;  /** process scpecial node, if not processed */

   /** variables for JAXP */
   private File schema;
   private DocumentBuilderFactory dbf;
   private DocumentBuilder db;
   private Document doc;

   /** variables for script */
   private File f;
   private FileWriter fw;
   private BufferedWriter bw;

   /** variables for algorithm */
   private int xmlSchemaID;
   private int counter;
   private String prefix;
   private ArrayList nodes;
   private ArrayList undefNodes;

   /***************************************************************************/
   /** Constructor */
   public XMLSchema2DB()
      {
      prefix     = new String();
      nodes      = new ArrayList();
      undefNodes = new ArrayList();
      } /* XMLSchema2DB */

   /***************************************************************************/
   /** Preparing new script for output. */
   private void prepareScript ()
      {
      try { f = new File(scrName); } catch (Exception e) { return; }

      // delete existing file
      if (f.exists())
         {
         f.delete();
         } /* if */

      // create new file
      try { fw = new FileWriter(f); } catch (Exception e) { return; }
      bw = new BufferedWriter(fw);
      } /* prepareScript */

   /***************************************************************************/
   /** Adds new statement into the script. */
   private void addToScript
      (
      String statement     /** added statement */
      )
      {
      String comm = statement;

      if (!statement.equals(""))
         {
         comm = statement + ";";
         } /* if */

      try { bw.write(comm,0,comm.length()); } catch (Exception e) { return; }
      try { bw.newLine(); } catch (Exception e) { return; }
      } /* addToScript */

   /***************************************************************************/
   /** CLoses the script. */
   private void closeScript()
      {
      try { bw.close(); } catch (Exception e) { return; }

      // null variables
      bw = null;
      fw = null;
      f  = null;
      } /* closeScript */

   /***************************************************************************/
   /******************************* PHASE I. **********************************/
   /***************************************************************************/

   /***************************************************************************/
   /** Common part of the printing */
   private void printlnCommon
      (
      Node n    /** node, whose content is printed */
      )
      {
      String val;

      // name of the node
      System.out.print(" name=\"" + n.getNodeName() + "\"");

      // value of the node
      val = n.getNodeValue();
      if (val != null)
         {
         System.out.print(" value=");
         if (!val.trim().equals(""))
            {
            System.out.print("\"" + n.getNodeValue() + "\"");
            } /* if */
         } /* if */
      System.out.println();
      } /* printlnCommon */

   /***************************************************************************/
   /** Recoursive printing of the content of DOM tree */
   private void echoDOMtree
      (
      Node n,      /** actually rocessed node */
      int indent   /** indentation */
      )
      {
      // print whitespace indentation
      for (int i = 0; i < indent; i++)
         {
         System.out.print("  ");
         } /* for */

      // print node type
      switch (n.getNodeType())
         {
         case Node.ATTRIBUTE_NODE          : System.out.print("ATTR:");
                                             printlnCommon(n);
                                             break;
         case Node.CDATA_SECTION_NODE      : System.out.print("CDATA:");
                                             printlnCommon(n);
                                             break;
         case Node.COMMENT_NODE            : System.out.print("COMM:");
                                             printlnCommon(n);
                                             break;
         case Node.DOCUMENT_FRAGMENT_NODE  : System.out.print("DOC_FRAG:");
                                             printlnCommon(n);
                                             break;
         case Node.DOCUMENT_NODE           : System.out.print("DOC:");
                                             printlnCommon(n);
                                             break;
         case Node.DOCUMENT_TYPE_NODE      : System.out.print("DOC_TYPE:");
                                             printlnCommon(n);

                                             // print entities if any
                                             NamedNodeMap nodeMap = ((DocumentType)n).getEntities();
                                             for (int i = 0; i < nodeMap.getLength(); i++)
                                                {
                                                Entity entity = (Entity)nodeMap.item(i);
                                                echoDOMtree(entity,indent + 1);
                                                } /* for */
                                             break;
         case Node.ELEMENT_NODE            : System.out.print("ELEM:");
                                             printlnCommon(n);

                                             // print attributes if any.
                                             NamedNodeMap atts = n.getAttributes();
                                             for (int i = 0; i < atts.getLength(); i++)
                                                {
                                                Node att = atts.item(i);
                                                echoDOMtree(att,indent + 1);
                                                } /* for */
                                             break;
         case Node.ENTITY_NODE             : System.out.print("ENT:");
                                             printlnCommon(n);
                                             break;
         case Node.ENTITY_REFERENCE_NODE   : System.out.print("ENT_REF:");
                                             printlnCommon(n);
                                             break;
         case Node.NOTATION_NODE           : System.out.print("NOTATION:");
                                             printlnCommon(n);
                                             break;
         case Node.PROCESSING_INSTRUCTION_NODE :
                                             System.out.print("PROC_INST:");
                                             printlnCommon(n);
                                             break;
         case Node.TEXT_NODE               : System.out.print("TEXT:");
                                             printlnCommon(n);
                                             break;
         default                           : System.out.print("UNSUPPORTED: " + n.getNodeType());
                                             printlnCommon(n);
                                             break;
         } /* switch */

      // print children if any
      for (Node child = n.getFirstChild(); child != null; child = child.getNextSibling())
         {
         if (child.getNodeType() != Node.TEXT_NODE)
            {
            echoDOMtree(child,indent + 1);
            } /* if */
         } /* for */
      } /* echoDOMtree */

   /***************************************************************************/
   /** Check prohibited elements (any, anyAttribute, import, include, redefine)
    *  and attributes (mixed for complexType and complexContent, substitutionGroup
    *  for elements). Returns true if the schema contains any, else returns false. */
   private boolean prohibitedElements ()
      {
      String pre1;
      Node sch;
      NodeList list;

      prefix = "";

      // get schema element and it's prefix
      sch  = doc.getFirstChild();
      pre1 = sch.getPrefix();
      if (pre1 != null)
         {
         prefix = pre1 + ":";
         } /* if */

      // check element any
      list = doc.getElementsByTagName(prefix + "any");
      if (list.getLength() > 0)
        {
        errMes = "XML schema contains prohibited element(s) 'any'.";
        return true;
        } /* if */

      // check element anyAttribute
      list = doc.getElementsByTagName(prefix + "anyAttribute");
      if (list.getLength() > 0)
        {
        errMes = "XML schema contains prohibited element(s) 'anyAttribute'.";
        return true;
        } /* if */

      // check element import
      list = doc.getElementsByTagName(prefix + "import");
      if (list.getLength() > 0)
        {
        errMes = "XML schema contains prohibited element(s) 'import'.";
        return true;
        } /* if */

      // check element include
      list = doc.getElementsByTagName(prefix + "include");
      if (list.getLength() > 0)
        {
        errMes = "XML schema contains prohibited element(s) 'include'.";
        return true;
        } /* if */

      // check element redefine
      list = doc.getElementsByTagName(prefix + "redefine");
      if (list.getLength() > 0)
        {
        errMes = "XML schema contains prohibited element(s) 'redefine'.";
        return true;
        } /* if */

      // check attribute mixed for element complexType
      list = doc.getElementsByTagName(prefix + "complexType");
      for (int i=0; i<list.getLength(); i++)
         {
         Node el = list.item(i);
         String val = getAttributeValue(el,"mixed");
         if ((val != null) && (val.equals("true")))
            {
            errMes = "XML schema allows prohibited mixed content.";
            return true;
            } /* if */
         } /* for */

      // check attribute mixed for element complexContent
      list = doc.getElementsByTagName(prefix + "complexContent");
      for (int i=0; i<list.getLength(); i++)
         {
         Node el = list.item(i);
         String val = getAttributeValue(el,"mixed");
         if ((val != null) && (val.equals("true")))
            {
            errMes = "XML schema allows prohibited mixed content.";
            return true;
            } /* if */
         } /* for */

      // check presence of sibstitutionGroups
      list = doc.getElementsByTagName(prefix + "element");
      for (int i=0; i<list.getLength(); i++)
         {
         Node el = list.item(i);
         String val = getAttributeValue(el,"substitutionGroup");
         if (val != null)
            {
            errMes = "XML schema contains prohibited substitution groups.";
            return true;
            } /* if */
         } /* for */

      return false;
      } /* prohibitedElements */

   /***************************************************************************/
   /** Check the presence of multiple namespaces.
    *  Returns true if the schema contains them, else returns false. */
   private boolean multipleNamespaces ()
      {
      Node sch, att;
      NamedNodeMap atts;
      int count = 0;
      String name;

      // get schema element and it's attributes
      sch  = doc.getFirstChild();
      atts = sch.getAttributes();
      for (int i = 0; i < atts.getLength(); i++)
         {
         att = atts.item(i);

         if (((att.getPrefix() == null) && (att.getLocalName().equals("xmlns"))) ||
             (att.getPrefix().equals("xmlns")))
            {
            count += 1;
            if (count > 1)
               {
               errMes = "XML schema's root element contains multiple namespaces.";
               return true;
               } /* if */
            } /* if */
         } /* for */

      return false;
      } /* multipleNamespaces */

   /***************************************************************************/
   /** Preparative and control operations for succesful transforming.
    *  Returns true if succeded, else returns false. */
   private boolean phase1_preparation
      (
      DBCall dbc     /** class for comunication with DB */
      )
      {
      String comm;

      // create and configure DocumentBuilderFactory
      dbf = DocumentBuilderFactory.newInstance();
      dbf.setNamespaceAware(true);                    // provide support for XML namespaces
      dbf.setIgnoringComments(true);                  // ignore comments
      dbf.setIgnoringElementContentWhitespace(true);  // eliminate whitespaces
      dbf.setCoalescing(true);                        // CDATA nodes to Text nodes
      dbf.setExpandEntityReferences(true);            // expand entity reference nodes

      // check deterministic content model (Xerces doesn't allow this)

      // create a DocumentBuilder that satisfies the constraints specified by
      // the DocumentBuilderFactory
      try
         {
         db = dbf.newDocumentBuilder();
         }
      catch (Exception e)
         {
         errMes = "Can't get DocumentBuilder class.";
         return false;
         } /* catch */

      // parse the input file
      try
         {
         doc = db.parse(schema);
         }
      catch (Exception e)
         {
         errMes = "XML schema is not well-formed.";
         return false;
         } /* catch */

/*
      // print DOM tree
      echoDOMtree(doc,0);
*/

      // check validity of the schema (Xerces doesn't allow this)
      Node n = doc.getFirstChild();
      if ((n.getLocalName() == null) || (!n.getLocalName().equals("schema")))
         {
         errMes = "XML schema is not valid.";
         return false;
         } /* if */

      // check the presence of prohibited elements (any, anyAttribute, import,
      // include, redefine)
      if (prohibitedElements())
         {
         return false;
         } /* if */

      // check the namespace
      if (multipleNamespaces())
         {
         return false;
         } /* if */

      // check members of choice with maxOccurs > 1
      NodeList list = doc.getElementsByTagName(prefix + "choice");
      for (int i=0; i<list.getLength(); i++)
         {
         Node el = list.item(i);
         for (Node child = el.getFirstChild(); child != null; child = child.getNextSibling())
            {
            String val = getAttributeValue(child,"maxOccurs");
            if (val != null)
               {
               Integer ii = new Integer(val);
               if (ii.intValue() > 1)
                  {
                  Element seq = doc.createElementNS("http://www.w3.org/2001/XMLSchema",prefix + "sequence");
                  child.getParentNode().replaceChild(seq, child);
                  seq.appendChild(child);
                  } /* if */
               } /* if */
            } /* for */
         } /* for */

      // check the existence of auxiliary tables (try to create them)
      comm = "CREATE TABLE xmlSchemaTable (\n" +
             "             uri VARCHAR2(200) UNIQUE, \n" +
             "             schemaID INT PRIMARY KEY, \n" +
             "             rootElemID INT UNIQUE)";
      if (dbc.executeUpdate(comm))
         {
         addToScript(comm);
         } /* if */
      comm = "CREATE TABLE xmlDocTable (\n" +
             "             schemaID INT, \n" +
             "             url VARCHAR2(200) UNIQUE, \n" +
             "             docID INT PRIMARY KEY)";
      if (dbc.executeUpdate(comm))
         {
         addToScript(comm);
         } /* if */
      comm = "CREATE TABLE xmlAttrTable (\n" +
             "             schemaID INT, \n" +
             "             attrID INT UNIQUE, \n" +
             "             xmlName VARCHAR2(200), \n" +
             "             mapType CHAR)";
      if (dbc.executeUpdate(comm))
         {
         addToScript(comm);
         } /* if */
      comm = "CREATE TABLE xmlElemTable (\n" +
             "             schemaID INT, \n" +
             "             elemID INT UNIQUE, \n" +
             "             xmlName VARCHAR2(200), \n" +
             "             mapType CHAR, \n" +
             "             elemType CHAR, \n" +
             "             udtName VARCHAR2(200))";
      if (dbc.executeUpdate(comm))
         {
         addToScript(comm);
         } /* if */
      comm = "CREATE TABLE xmlElemAttrTable (\n" +
             "             schemaID INT, \n" +
             "             elemID INT, \n" +
             "             attrID INT, \n" +
             "             ord INT)";
      if (dbc.executeUpdate(comm))
         {
         addToScript(comm);
         } /* if */
      comm = "CREATE TABLE xmlElemElemTable (\n" +
             "             schemaID INT, \n" +
             "             elemID INT, \n" +
             "             subElemID INT, \n" +
             "             ord INT, \n" +
             "             mapType CHAR, \n" +
             "             min INT, \n" +
             "             max INT)";
      if (dbc.executeUpdate(comm))
         {
         addToScript(comm);
         } /* if */
      comm = "CREATE TYPE arrayOfIDs AS VARRAY(200) OF INT";
      if (dbc.executeUpdate(comm))
         {
         addToScript(comm);
         } /* if */
      comm = "CREATE TABLE xmlPathTable (\n" +
             "             schemaID INT, \n" +
             "             elemID INT, \n" +
             "             subElemID INT, \n" +
             "             path arrayOfIDs)";
      if (dbc.executeUpdate(comm))
         {
         addToScript(comm);
         } /* if */
      comm = "CREATE SEQUENCE xmlSchemaSeq START WITH 1 INCREMENT BY 1 NOCACHE NOCYCLE";      // sequence of IDs for XML schemas
      if (dbc.executeUpdate(comm))
         {
         addToScript(comm);
         } /* if */
      comm = "CREATE SEQUENCE elemSeq START WITH 1 INCREMENT BY 1 NOCACHE NOCYCLE";           // sequence of IDs for elements
      if (dbc.executeUpdate(comm))
         {
         addToScript(comm);
         } /* if */
      comm = "CREATE SEQUENCE attrSeq START WITH 1 INCREMENT BY 1 NOCACHE NOCYCLE";           // sequence of IDs for attributes
      if (dbc.executeUpdate(comm))
         {
         addToScript(comm);
         } /* if */
      comm = "CREATE SEQUENCE udtSeq START WITH 1 INCREMENT BY 1 NOCACHE NOCYCLE";           // sequence of IDs for attributes
      if (dbc.executeUpdate(comm))
         {
         addToScript(comm);
         } /* if */
      comm = "CREATE TYPE udtInterval AS OBJECT (\n" +
             "            yea_mon INTERVAL YEAR TO MONTH,\n" +
             "            day_sec INTERVAL DAY TO SECOND )";           // type for intervals
      if (dbc.executeUpdate(comm))
         {
         addToScript(comm);
         } /* if */

      // check whether the schema wasn't already mapped
      if (!dbc.executeQuery("SELECT * FROM xmlSchemaTable WHERE uri = '" +
                            schema.getAbsolutePath() + "'"))
         {
         errMes = "Error while processing SQL statement.";
         return false;
         } /* if */
      if (dbc.rsAnyResults())
         {
         errMes = "This XML schema was already mapped.";
         return false;
         } /* if */

      // generate and store XML schema ID
      xmlSchemaID = dbc.getNEXTVAL("xmlSchemaSeq");
      if (xmlSchemaID == -1)
         {
         return false;
         } /* if */

      // insert a record into xmlSchemaTable
      comm = "INSERT INTO xmlSchemaTable \n" +
             "   VALUES ('" + schema.getAbsolutePath() + "', " + xmlSchemaID + ", NULL)";
      if (!dbc.executeUpdate(comm))
         {
         errMes = "Error while processing SQL statement.";
         return false;
         } /* if */
      addToScript(comm);

      // create ancestor of all UDTs
      comm = "CREATE TYPE xmlAncestor_" + xmlSchemaID + " AS OBJECT (\n" +
             "            elemID INT, recordID INT ) NOT FINAL";
      if (!dbc.executeUpdate(comm))
         {
         errMes = "Error while processing SQL statement.";
         return false;
         } /* if */
      addToScript(comm);

      addToScript("");

      return true;
      } /* phase1_preparation */

   /***************************************************************************/
   /******************************* PHASE II. *********************************/
   /***************************************************************************/

   /***************************************************************************/
   /** Recoursive creating of AddData class and setting it as UserData
    *  for supported elements. */
   private void createAditionalData
      (
      Node n      /** actually rocessed node */
      )
      {
      NodeImpl ni = (NodeImpl)n;
      AddData ad;

      // check node type (we are interested only in elements, attributes and
      // document node)
      if ((n.getNodeType() != Node.DOCUMENT_NODE) &&
          (n.getNodeType() != Node.ELEMENT_NODE) &&
          (n.getNodeType() != Node.ATTRIBUTE_NODE))
         {
         ni.setUserData(null);
         return;
         } /* if */

      // check element type (we aren't interested in notation, annotation, key,
      // keyref and unique elements)
      if (n.getNodeType() == Node.ELEMENT_NODE)
         {
         if (n.getLocalName().equals("notation") ||
             n.getLocalName().equals("annotation") ||
             n.getLocalName().equals("key") ||
             n.getLocalName().equals("keyref") ||
             n.getLocalName().equals("unique"))
            {
            ni.setUserData(null);
            return;
            } /* if */
         } /* if */

      // create new AddData class
      ad = new AddData(counter++);
      ni.setUserData(ad);
      ad.setMyNode(n);

      // process attributes if any
      if (n.getNodeType() == Node.ELEMENT_NODE)
         {
         NamedNodeMap atts = n.getAttributes();
         for (int i = 0; i < atts.getLength(); i++)
            {
            Node att = atts.item(i);
            createAditionalData(att);
            } /* for */
         } /* if */

      // process children elements if any
      for (Node child = n.getFirstChild(); child != null; child = child.getNextSibling())
         {
         createAditionalData(child);
         } /* for */
      } /* createAditionalData */

   /***************************************************************************/
   /** Recoursive adding new edges. */
   private void addNewEdges
      (
      Node n      /** actually rocessed node */
      )
      {
      NodeImpl ni = (NodeImpl)n;
      AddData  ad = (AddData)ni.getUserData();

      // check whether it is interesting node (it's UserData variable was set)
      if (ad == null)
         {
         return;
         } /* if */

      // new edges can occur only for attributes
      if (n.getNodeType() == Node.ATTRIBUTE_NODE)
         {
         // new edges can occur only for attributes base, type or ref
         if (n.getLocalName().equals("base") ||
             n.getLocalName().equals("type") ||
             n.getLocalName().equals("itemType") ||
             n.getLocalName().equals("ref"))
            {
            String value = n.getNodeValue();

            // try to find referenced node among global defined nodes
            Node sch = doc.getFirstChild();            // element schema
            for (Node child = sch.getFirstChild(); child != null; child = child.getNextSibling())
               {
               // attribute nodes of element schema
               NamedNodeMap atts = child.getAttributes();
               if (atts == null)
                  {
                  continue;
                  } /* if */
               for (int i = 0; i < atts.getLength(); i++)
                  {
                  Node att = atts.item(i);

                  if ((att.getNodeName().equals("name")) && (att.getNodeValue().equals(value)))
                     {
                     ad.setGlobalNode(child);
                     } /* if */
                  } /* for */
               } /* for */
            } /* if */
         } /* if */

      // process attributes if any
      if (n.getNodeType() == Node.ELEMENT_NODE)
         {
         NamedNodeMap atts = n.getAttributes();
         for (int i = 0; i < atts.getLength(); i++)
            {
            Node att = atts.item(i);
            addNewEdges(att);
            } /* for */
         } /* if */

      // process children elements if any
      for (Node child = n.getFirstChild(); child != null; child = child.getNextSibling())
         {
         addNewEdges(child);
         } /* for */
      } /* addNewEdges */

   /***************************************************************************/
   /** Creating a DOM graph from DOM tree (including cycles and unused elements).
    *  Returns true if succeded, else returns false. */
   private boolean createDOMgraph ()
      {
      // set UserData for processed nodes of the tree (recoursively)
      counter = 1;
      createAditionalData(doc);

      // add new edges to global defined nodes (recoursively)
      addNewEdges(doc);

      return true;
      } /* createDOMgraph */

   /***************************************************************************/
   /** Recoursive printing of the content of DOM graph */
   private void echoDOMgraph
      (
      Node n,              /** actually rocessed node */
      int indent,          /** indentation */
      boolean spec         /** true == special edge,
                               false == original DOM tree edge */
      )
      {
      NodeImpl ni = (NodeImpl)n;
      AddData  ad = (AddData)ni.getUserData();

      // check whether it is interesting node (it's UserData variable was set)
      if (ad == null)
         {
         return;
         } /* if */

      // print whitespace indentation
      for (int i = 0; i < indent; i++)
         {
         System.out.print("  ");
         } /* for */

      // type of edge
      if (spec)
         {
         System.out.print("SPE ");
         }
      else
         {
         System.out.print("ORD ");
         } /* else */

      // print node type
      switch (n.getNodeType())
         {
         case Node.ATTRIBUTE_NODE          : System.out.print("ATTR:");
                                             printlnCommon(n);
                                             break;
         case Node.DOCUMENT_NODE           : System.out.print("DOC:");
                                             printlnCommon(n);
                                             break;
         case Node.ELEMENT_NODE            : System.out.print("ELEM:");
                                             printlnCommon(n);

                                             // print attributes if any.
                                             NamedNodeMap atts = n.getAttributes();
                                             for (int i = 0; i < atts.getLength(); i++)
                                                {
                                                Node att = atts.item(i);
                                                echoDOMgraph(att,indent + 1, false);
                                                } /* for */
                                             break;
         default                           : System.out.print("UNSUPPORTED: " + n.getNodeType());
                                             printlnCommon(n);
                                             break;
         } /* switch */

      // print special element edges if any
      Node glob = ad.getGlobalNode();
      if (glob != null)
         {
         echoDOMgraph(glob,indent + 1, true);
         } /* if */

      // print children element edges if any
      for (Node child = n.getFirstChild(); child != null; child = child.getNextSibling())
         {
         echoDOMgraph(child,indent + 1, false);
         } /* for */
      } /* echoDOMgraph */

   /***************************************************************************/
   /** Preprocessing of cycles from DOM graph. */
   private boolean preprocessComplexType
      (
      Node child,    /** complexType node */
      boolean spec,  /**  special value */
      DBCall dbc     /** class for comunication with DB */
      )
      {
      NodeImpl ni = (NodeImpl)child;
      AddData  ad = (AddData)ni.getUserData();
      int udtID = dbc.getNEXTVAL("udtSeq");
      if (udtID == -1)
         {
         return false;
         } /* if */

      // create incomplete type
      String comm = "CREATE TYPE E_" + xmlSchemaID + "_" + udtID;
      if (!dbc.executeUpdate(comm))
         {
         return false;
         } /* if */
      addToScript(comm);

      // store name
      ad.setUdtName("E_" + xmlSchemaID + "_" + udtID,udtID);

      // special node
      ad.setSpecial(spec);

      return true;
      } /* preprocessComplexType */

   /***************************************************************************/
   /** Preprocessing of cycles from DOM graph. */
   private boolean preprocessGlobalNodes
      (
      DBCall dbc     /** class for comunication with DB */
      )
      {
      Node n = doc.getFirstChild();
      Node comp = null;

      // process globally defined complex types
      for (Node child = n.getFirstChild(); child != null; child = child.getNextSibling())
         {
         NodeImpl ni = (NodeImpl)child;
         AddData  ad = (AddData)ni.getUserData();

         if (child.getNodeType() != Node.ELEMENT_NODE)
            {
            continue;
            } /* if */
         if (ad == null)
            {
            continue;
            } /* if */
         if (!child.getLocalName().equals("complexType"))
            {
            continue;
            } /* if */

         // preprocess complex types
         if (!preprocessComplexType(child,true,dbc))
            {
            return false;
            } /* if */
         } /* for */

      // process globally defined elements
      for (Node child = n.getFirstChild(); child != null; child = child.getNextSibling())
         {
         comp = null;
         NodeImpl ni = (NodeImpl)child;
         AddData  ad = (AddData)ni.getUserData();

         if (child.getNodeType() != Node.ELEMENT_NODE)
            {
            continue;
            } /* if */
         if (ad == null)
            {
            continue;
            } /* if */
         if (!child.getLocalName().equals("element"))
            {
            continue;
            } /* if */

         // try to find locally defined complexType
         for (Node ch = child.getFirstChild(); ch != null; ch = ch.getNextSibling())
            {
            NodeImpl cni = (NodeImpl)ch;
            AddData  cad = (AddData)cni.getUserData();

            if (ch.getNodeType() != Node.ELEMENT_NODE)
               {
               continue;
               } /* if */
            if (cad == null)
               {
               continue;
               } /* if */
            if (!ch.getLocalName().equals("complexType"))
               {
               continue;
               } /* if */

            if (!preprocessComplexType(ch,false,dbc))
               {
               return false;
               } /* if */
            comp = ch;
            break;
            } /* for */

         // preprocess globally defined node
         if (comp == null)
            {
            NodeImpl ni_att = (NodeImpl)getAttribute(child,"type");
            if (ni_att == null)
               {
               continue;
               } /* if */
            AddData  ad_att = (AddData)ni_att.getUserData();
            comp = ad_att.getGlobalNode();
            if (comp == null)
               {
               continue;
               } /* if */
            } /* if */

         // process element
         NodeImpl coni = (NodeImpl)comp;
         AddData  coad = (AddData)coni.getUserData();
         ad.setUdtName(coad.getUdtName(),coad.getUdtID());
         ad.setSpecial(true);

         // generate element ID
         int elemID = dbc.getNEXTVAL("elemSeq");
         if (elemID == -1)
            {
            return false;
            } /* if */
         ad.setElemID(elemID);
         } /* for */

      return true;
      } /* preprocessGlobalNodes */

   /***************************************************************************/
   /** Creating a DOM graph from DOM tree, including elimination of cycles.
    *  Returns true if succeded, else returns false. */
   private boolean phase2_createDOMgraph
      (
      DBCall dbc     /** class for comunication with DB */
      )
      {
      // create DOM graph (including cycles and unused elements)
      if (!createDOMgraph())
         {
         return false;
         } /* if */
/*
      // print DOM graph
      echoDOMgraph(doc, 0, false);
*/
      // remove cycles from DOM graph
      preprocessGlobalNodes(dbc);
      addToScript("");

      return true;
      } /* phase2_createDOMgraph */

   /***************************************************************************/
   /******************************* PHASE III. ********************************/
   /***************************************************************************/

   /***************************************************************************/
   /** Returns attribute with specified name or null. */
   private Node getAttribute
      (
      Node n,              /** actually processed node */
      String name          /** name of the attribute */
      )
      {
      if (n.getNodeType() == Node.ELEMENT_NODE)
         {
         NamedNodeMap atts = n.getAttributes();
         for (int i = 0; i < atts.getLength(); i++)
            {
            Node att = atts.item(i);
            if (att.getLocalName().equals(name))
               {
               return att;
               } /* if */
            } /* for */
         } /* if */

      return null;
      } /* getAttribute */

   /***************************************************************************/
   /** Returns value of attribute with specified name. Returns string value of
    *  the attribute or null. */
   private String getAttributeValue
      (
      Node n,              /** actually processed node */
      String name          /** name of the attribute */
      )
      {
      if (n.getNodeType() == Node.ELEMENT_NODE)
         {
         NamedNodeMap atts = n.getAttributes();
         for (int i = 0; i < atts.getLength(); i++)
            {
            Node att = atts.item(i);
            if (att.getLocalName().equals(name))
               {
               return att.getNodeValue();
               } /* if */
            } /* for */
         } /* if */

      return null;
      } /* getAttributeValue */

   /***************************************************************************/
   /** Returns subelement with specified name or null. */
   private Node getSubelement
      (
      Node n,              /** actually processed node */
      String name          /** name of the subelement */
      )
      {
      for (Node child = n.getFirstChild(); child != null; child = child.getNextSibling())
         {
         if (child.getNodeType() == Node.ELEMENT_NODE)
            {
            if (child.getLocalName().equals(name))
               {
               return child;
               } /* if */
            } /* if */
         } /* for */

      return null;
      } /* getSubelement */

   /***************************************************************************/
   /** Processing list DOM graph node. Returns true if succeded, else
    *  returns false. */
   private boolean processList
      (
      Node n,        /** actually processed node */
      AddData pad,   /** parent node additional data */
      DBCall dbc     /** class for comunication with DB */
      )
      {
      Node sub = getSubelement(n,"simpleType");
      if (sub != null)  // simple type is specified by subelement
         {
         if (!processDOMnode(sub,pad,dbc,UN))
            {
            return false;
            } /* if */
         }
      else              // simple type is specified by attribute
         {
         NodeImpl ni_att = (NodeImpl)getAttribute(n,"itemType");
         AddData  ad_att = (AddData)ni_att.getUserData();
         Node       glob = ad_att.getGlobalNode();

         if (glob != null)    // globally defined simple type
            {
            NodeImpl ni_gl = (NodeImpl)glob;
            AddData  ad_gl = (AddData)ni_gl.getUserData();
            if (!processDOMnode(glob,ad_gl,dbc,UN))
               {
               return false;
               } /* if */
            pad.copyContent(ad_gl);
            }
         else                 // built-in datatype
            {
            pad.setSimpleType(getAttributeValue(n,"itemType"),prefix);
            } /* else */
         } /* else */

      // set list properties
      pad.setList();

      return true;
      } /* processList */

   /***************************************************************************/
   /** Processing restriction DOM graph node with simpleType parrent node.
    *  Returns true if succeded, else returns false. */
   private boolean processSTrestriction
      (
      Node n,       /** actually processed node */
      AddData pad,  /** parent node additional data */
      DBCall dbc    /** class for comunication with DB */
      )
      {
      // proces restricted simple type
      Node sub = getSubelement(n,"simpleType");
      if (sub != null)  // simple type is specified by subelement
         {
         if (!processDOMnode(sub,pad,dbc,UN))
            {
            return false;
            } /* if */
         }
      else              // simple type is specified by attribute
         {
         NodeImpl ni_att = (NodeImpl)getAttribute(n,"base");
         AddData  ad_att = (AddData)ni_att.getUserData();
         Node       glob = ad_att.getGlobalNode();

         if (glob != null)    // globally defined simple type
            {
            NodeImpl ni_gl = (NodeImpl)glob;
            AddData  ad_gl = (AddData)ni_gl.getUserData();
            if (!processDOMnode(glob,ad_gl,dbc,UN))
               {
               return false;
               } /* if */
            pad.copyContent(ad_gl);
            }
         else                 // built-in datatype
            {
            pad.setSimpleType(getAttributeValue(n,"base"),prefix);
            } /* else */
         } /* else */

      // process all subelements (new restrictions), except simpleType, if present
      for (Node child = n.getFirstChild(); child != null; child = child.getNextSibling())
         {
         if (child.getNodeType() == Node.ELEMENT_NODE)
            {
            if (!child.getLocalName().equals("simpleType") &&
                !child.getLocalName().equals("attribute") &&
                !child.getLocalName().equals("attributeGroup"))
               {
               if (!processDOMnode(child,pad,dbc,UN))
                  {
                  return false;
                  } /* if */
               } /* if */
            } /* if */
         } /* for */

      return true;
      } /* processSTrestriction */

   /***************************************************************************/
   /** Processing attribute DOM graph node. Returns true if succeded, else
    *  returns false. */
   private boolean processAttribute
      (
      Node n,        /** actually processed node */
      AddData pad,   /** parent node additional data */
      DBCall dbc     /** class for comunication with DB */
      )
      {
      boolean ref = false;

      // proces restricted simple type
      Node sub = getSubelement(n,"simpleType");
      if (sub != null)  // simple type is specified by subelement
         {
         if (!processDOMnode(sub,pad,dbc,UN))
            {
            return false;
            } /* if */
         }
      else              // simple type is specified by one of it's attributes
         {
         NodeImpl ni_att = (NodeImpl)getAttribute(n,"type");
         if (ni_att != null)     // simple type is specified by attribute type
            {
            AddData ad_att = (AddData)ni_att.getUserData();
            Node      glob = ad_att.getGlobalNode();

            if (glob != null)    // globally defined simple type
               {
               NodeImpl ni_gl = (NodeImpl)glob;
               AddData  ad_gl = (AddData)ni_gl.getUserData();
               if (!processDOMnode(glob,ad_gl,dbc,UN))
                  {
                  return false;
                  } /* if */
               pad.copyContent(ad_gl);
               }
            else                 // built-in datatype
               {
               pad.setSimpleType(getAttributeValue(n,"type"),prefix);
               } /* else */
            }
         else                    // globally defined attribute (define by attribute ref)
            {
            ni_att         = (NodeImpl)getAttribute(n,"ref");
            AddData ad_att = (AddData)ni_att.getUserData();
            Node      glob = ad_att.getGlobalNode();

            NodeImpl ni_gl = (NodeImpl)glob;
            AddData  ad_gl = (AddData)ni_gl.getUserData();
            if (!processDOMnode(glob,ad_gl,dbc,UN))
               {
               return false;
               } /* if */
            pad.copyContent(ad_gl);
            ref = true;
            } /* else */
         } /* else */

      // process attributes
      pad.setFixed(getAttributeValue(n,"fixed"));
      pad.setUse(getAttributeValue(n,"use"));

      // referenced attribute already has id and record
      if (ref)
         {
         return true;
         } /* if */

      // generate attribute ID
      int attrID = dbc.getNEXTVAL("attrSeq");
      if (attrID == -1)
         {
         return false;
         } /* if */

      // insert a record into xmlAttrTable
      String comm = "INSERT INTO xmlAttrTable \n" +
                    "   VALUES (" + xmlSchemaID + ", "
                                  + attrID + ", '"
                                  + getAttributeValue(n,"name") + "', '"
                                  + pad.getMapType() + "')";
      if (!dbc.executeUpdate(comm))
         {
         errMes = "Error while processing SQL statement.";
         return false;
         } /* if */
      addToScript(comm);
      addToScript("");

      // store attribute ID
      pad.setAttrID(attrID);

      return true;
      } /* processAttribute */

   /***************************************************************************/
   /** Processing attributeGroup DOM graph node. Returns true if succeded, else
    *  returns false. */
   private boolean processAttributeGroup
      (
      Node n,       /** actually processed node */
      AddData pad,  /** parent node additional data */
      DBCall dbc    /** class for comunication with DB */
      )
      {
      NodeImpl ni_att = (NodeImpl)getAttribute(n,"ref");
      if (ni_att != null)     // attributeGroup is referenced
         {
         AddData ad_att = (AddData)ni_att.getUserData();
         Node      glob = ad_att.getGlobalNode();

         NodeImpl ni_gl = (NodeImpl)glob;
         AddData  ad_gl = (AddData)ni_gl.getUserData();
         if (!processDOMnode(glob,ad_gl,dbc,UN))
            {
            return false;
            } /* if */
         pad.copyContent(ad_gl);
         }
      else                    // process attributes and attributeGroups
         {
         if (!processAtts(n,pad,dbc))
            {
            return false;
            } /* else */
         } /* else */

      return true;
      } /* processAttributeGroup */

   /***************************************************************************/
   /** Processing attributes and attributeGroups of given node.
    *  Returns true if succeded, else returns false. */
   private boolean processAtts
      (
      Node n,       /** actually processed node */
      AddData pad,  /** parent node additional data */
      DBCall dbc    /** class for comunication with DB */
      )
      {
      for (Node child = n.getFirstChild(); child != null; child = child.getNextSibling())
         {
         if (child.getNodeType() != Node.ELEMENT_NODE)
            {
            continue;
            } /* if */
         // check interesting subelements
         if (!child.getLocalName().equals("attributeGroup") &&
             !child.getLocalName().equals("attribute"))
            {
            continue;
            } /* if */

         // process child node
         NodeImpl ni = (NodeImpl)child;
         AddData  ad = (AddData)ni.getUserData();
         if (!processDOMnode(child,ad,dbc,UN))
            {
            return false;
            } /* if */

         // collect IDs
         if (child.getLocalName().equals("attributeGroup"))
            {
            pad.addAttrList(ad.getAttrList());
            }
         else // attribute
            {
            pad.addAttrList(ad);
            } /* else */
         } /* for */

      return true;
      } /* processAtts */

   /***************************************************************************/
   /** Processing extension DOM graph node with simpleContent parrent node.
    *  Returns true if succeded, else returns false. */
   private boolean processSCextension
      (
      Node n,       /** actually processed node */
      AddData pad,  /** parent node additional data */
      DBCall dbc    /** class for comunication with DB */
      )
      {
      // process base attribute
      NodeImpl ni_att = (NodeImpl)getAttribute(n,"base");
      AddData  ad_att = (AddData)ni_att.getUserData();
      Node       glob = ad_att.getGlobalNode();

      if (glob != null)    // globally defined simple type
         {
         NodeImpl ni_gl = (NodeImpl)glob;
         AddData  ad_gl = (AddData)ni_gl.getUserData();
         if (!processDOMnode(glob,ad_gl,dbc,UN))
            {
            return false;
            } /* if */
         pad.copyContent(ad_gl);
         }
      else                 // built-in datatype
         {
         pad.setSimpleType(getAttributeValue(n,"base"),prefix);
         } /* else */

      // process attributes and attributeGroups
      if (!processAtts(n,pad,dbc))
         {
         return false;
         } /* else */

      return true;
      } /* processSCextension */

   /***************************************************************************/
   /** Processing restriction DOM graph node with simpleContent parrent node.
    *  Returns true if succeded, else returns false. */
   private boolean processSCrestriction
      (
      Node n,       /** actually processed node */
      AddData pad,  /** parent node additional data */
      DBCall dbc    /** class for comunication with DB */
      )
      {
      // process simple type and it's restrictions
      if (!processSTrestriction(n,pad,dbc))
         {
         return false;
         } /* if */

      // process attributes and attributeGroups
      if (!processAtts(n,pad,dbc))
         {
         return false;
         } /* else */

      return true;
      } /* processSCrestriction */

   /***************************************************************************/
   /** Processing complexContent DOM graph node. Returns true if succeded, else
    *  returns false. */
   private boolean processComplexContent
      (
      Node n,              /** actually processed node */
      AddData pad,         /** parent node additional data */
      DBCall dbc,          /** class for comunication with DB */
      boolean abs          /** abstract type */
      )
      {
      String type = null;      // type of child node (simpleType, complexType,
                               // group, all, choice or sequence)
      String under = "xmlAncestor_" + xmlSchemaID;    // name of ancestor of this udt
      AddData ch_ad = null;    // child node (group, all choice, sequence)
      String abstr = " ";      // string for abstract udts
      AddData an_ad = null;    // ancestor node (extension of complexContent)
      int begAttr = 0;         // beginning of attributes for this udt
      int begElem = 0;         // beginning of elements for this udt
      int order   = 1;         // first index of order for added attribute
      String desc;
      Node ext = null;

      // process content child nodes
      for (Node child = n.getFirstChild(); child != null; child = child.getNextSibling())
         {
         if (child.getNodeType() != Node.ELEMENT_NODE)
            {
            continue;
            } /* if */

         // process child node according to it's type
         if (child.getLocalName().equals("restriction"))
            {
            // process base attribute
            NodeImpl ni_att = (NodeImpl)getAttribute(child,"base");
            AddData  ad_att = (AddData)ni_att.getUserData();
            Node       glob = ad_att.getGlobalNode();

            NodeImpl ni_gl = (NodeImpl)glob;
            AddData  ad_gl = (AddData)ni_gl.getUserData();

            if (!processDOMnode(glob,ad_gl,dbc,SPEC))
               {
               return false;
               } /* if */
            pad.copyContent(ad_gl);
            return true;
            }
         else if (child.getLocalName().equals("extension"))
            {
            ext = child;
            // process extended type
            NodeImpl ni_att = (NodeImpl)getAttribute(child,"base");
            String      val = getAttributeValue(child,"base");
            if (!val.equals("anyType"))    // only type different from anyType
               {
               an_ad         = (AddData)ni_att.getUserData();
               NodeImpl glob = (NodeImpl)an_ad.getGlobalNode();
               an_ad         = (AddData)glob.getUserData();
               if (!processDOMnode(glob,an_ad,dbc,SPEC))
                  {
                  return false;
                  } /* if */
               } /* if */

            // process content of extension
            for (Node ch = child.getFirstChild(); ch != null; ch = ch.getNextSibling())
               {
               if (ch.getNodeType() != Node.ELEMENT_NODE)
                  {
                  continue;
                  } /* if */

               // process child node according to it's type
               if (ch.getLocalName().equals("group") ||
                   ch.getLocalName().equals("all") ||
                   ch.getLocalName().equals("choice") ||
                   ch.getLocalName().equals("sequence"))
                  {
                  NodeImpl cni = (NodeImpl)ch;
                  ch_ad = (AddData)cni.getUserData();
                  if (!processDOMnode(ch,ch_ad,dbc,UN))
                     {
                     return false;
                     } /* if */
                  break;
                  }
               else
                  {
                  continue;
                  } /* else */
               } /* for */
            }
         else     // unsupported node
            {
            continue;
            } /* else */
         break;
         } /* for */

      // process extended type, if any
      if (an_ad != null)
         {
         // change name of extended type
         under = an_ad.getUdtName();

         // copy attribute list
         pad.addAttrList(an_ad.getAttrList());

         // copy subelement list
         pad.addElemList(an_ad.getElemList());

         // set order and beginning
         begAttr = pad.getAttrListSize();
         begElem = pad.getElemListSize();

         order   = begAttr + begElem + 1;
         } /* if */

      // process attributes and attributeGroups
      if (!processAtts(ext,pad,dbc))
         {
         return false;
         } /* else */

      // add subelement to list
      if (ch_ad != null)
         {
         pad.addElemList(ch_ad);
         } /* if */
      pad.setComplexMapType();

      // process interesting attributes
      if (abs)
         {
         abstr = " NOT ";
         } /* if */

      // generate UDT ID
      // generate UDT ID
      String udt = pad.getUdtName();
      int udtID = pad.getUdtID();
      if (udt == null)
         {
         udtID = dbc.getNEXTVAL("udtSeq");
         if (udtID == -1)
            {
            return false;
            } /* if */
         udt = "E_" + xmlSchemaID + "_" + udtID;
         } /* if */

      // create beginning of UDT
      String comm = "CREATE TYPE " + udt + " UNDER " + under + " (\n";

      // add attributes to UDT
      ArrayList al = pad.getAttrList();
      for (int i=begAttr; i<al.size(); i++)
         {
         AddData ad = (AddData)al.get(i);

         // set order
         ad.setOrder(order++);

         // get description if simple type
         desc = ad.getSimpleTypeDesc(udt,"A_");

         // check whether the string contains auxiliary command
         if (desc.indexOf("A_") != 0)
            {
            String aux = desc.substring(0,desc.indexOf("A_"));
            if (!dbc.executeUpdate(aux))
               {
               return false;
               } /* if */
            addToScript(aux);

            // clear auxiliary command from the string
            desc = desc.substring(desc.indexOf("A_"),desc.length());
            } /* if */
         comm = comm + "            " + desc + ",\n";
         } /* for */

      // add content, if any
      if (ch_ad != null)
         {
         al = pad.getElemList();
         ch_ad = (AddData)al.get(begElem);
         ch_ad.setOrder(order);

         // get description of content
         desc = ch_ad.getComplexTypeDesc(udt,"C_");

         // check whether the string contains auxiliary command
         if (desc.indexOf("C_") != 0)
            {
            String aux = desc.substring(0,desc.indexOf("C_"));
            // process auxiliary command
            if (!dbc.executeUpdate(aux))
               {
               return false;
               } /* if */
            addToScript(aux);

            // clear auxiliary command from the string
            desc = desc.substring(desc.indexOf("C_"),desc.length());
            } /* if */
         comm = comm + "            " + desc + ",\n";
         } /* if */

      // add end of UDT
      comm = comm.substring(0,comm.length()-2) +  " )" + abstr + "INSTANTIABLE NOT FINAL";

      // execute the query
      if (!dbc.executeUpdate(comm))
         {
         return false;
         } /* if */
      addToScript(comm);

      // add name of UDT do DOM graph
      pad.setUdtName(udt,udtID);
      nodes.add(pad);

      return true;
      } /* processComplexContent */

   /***************************************************************************/
   /** Processing complexType DOM graph node. Returns true if succeded, else
    *  returns false. */
   private boolean processComplexType
      (
      Node n,              /** actually processed node */
      AddData pad,         /** parent node additional data */
      DBCall dbc           /** class for comunication with DB */
      )
      {
      String type = null;      // name of child node
      String abstr = " ";      // string for abstract udts
      AddData ch_ad = null;    // child node (group, all choice, sequence)
      int begElem = 0;         // beginning of elements for this udt
      int order   = 1;         // first index of order for added attribute
      String desc;

      // process interesting attributes
      String val = getAttributeValue(n,"abstract");
      if (val != null)
         {
         if (val.equals("true"))
            {
            abstr = " NOT ";
            } /* if */
         } /* if */

      // process content child nodes
      for (Node child = n.getFirstChild(); child != null; child = child.getNextSibling())
         {
         if (child.getNodeType() != Node.ELEMENT_NODE)
            {
            continue;
            } /* if */

         // process child node according to it's type
         if (child.getLocalName().equals("simpleContent"))
            {
            if (!processDOMnode(child,pad,dbc,UN))
               {
               return false;
               } /* if */
            ch_ad = pad;
            }
         else if (child.getLocalName().equals("group") ||
                  child.getLocalName().equals("all") ||
                  child.getLocalName().equals("choice") ||
                  child.getLocalName().equals("sequence"))
            {
            NodeImpl cni = (NodeImpl)child;
            ch_ad = (AddData)cni.getUserData();
            if (!processDOMnode(child,ch_ad,dbc,UN))
               {
               return false;
               } /* if */
            }
         else if (child.getLocalName().equals("complexContent"))
            {
            if (!processComplexContent(child,pad,dbc,(val != null)))
               {
               return false;
               } /* if */
            return true;
            }
         else     // unsupported node
            {
            continue;
            } /* else */

         type = child.getLocalName();
         break;
         } /* for */

      // process attributes and attributeGroups
      if (!processAtts(n,pad,dbc))
         {
         return false;
         } /* else */

      // add subelement to list
      if ((ch_ad != null) && (!type.equals("simpleContent")))
         {
         pad.addElemList(ch_ad);
         pad.setComplexMapType();
         } /* if */

      // generate UDT ID
      String udt = pad.getUdtName();
      int udtID = pad.getUdtID();
      if (udt == null)
         {
         udtID = dbc.getNEXTVAL("udtSeq");
         if (udtID == -1)
            {
            return false;
            } /* if */
         udt = "E_" + xmlSchemaID + "_" + udtID;
         } /* if */

      // create beginning of UDT
      String comm = "CREATE TYPE " + udt + " UNDER xmlAncestor_" + xmlSchemaID + " (\n";

      // add attributes, if any
      ArrayList al = pad.getAttrList();
      for (int i=0; i<al.size(); i++)
         {
         AddData ad = (AddData)al.get(i);

         // set order
         ad.setOrder(order++);

         // get description if simple type
         desc = ad.getSimpleTypeDesc(udt,"A_");

         // check whether the string contains auxiliary command
         if (desc.indexOf("A_") != 0)
            {
            String aux = desc.substring(0,desc.indexOf("A_"));
            if (!dbc.executeUpdate(aux))
               {
               return false;
               } /* if */
            addToScript(aux);

            // clear auxiliary command from the string
            desc = desc.substring(desc.indexOf("A_"),desc.length());
            } /* if */
         comm = comm + "            " + desc + ",\n";
         } /* for */

      // add content, if any
      if (ch_ad != null)     // either simpleContent or any model group (can be empty)
         {
         al = pad.getElemList();
         begElem = al.size() - 1;
         if (!type.equals("simpleContent"))
            {
            ch_ad = (AddData)al.get(begElem);
            } /* if */
         ch_ad.setOrder(order);

         // get description of content
         if (type.equals("simpleContent"))    //  simple type of content
            {
            desc = ch_ad.getSimpleTypeDesc(udt,"C_");
            }
         else                                 // group, all, choice, sequence, extension of complexContent
            {
            desc = ch_ad.getComplexTypeDesc(udt,"C_");
            } /* else */

         // check whether the string contains auxiliary command
         if (desc.indexOf("C_") != 0)
            {
            String aux = desc.substring(0,desc.indexOf("C_"));
            // process auxiliary command
            if (!dbc.executeUpdate(aux))
               {
               return false;
               } /* if */
            addToScript(aux);

            // clear auxiliary command from the string
            desc = desc.substring(desc.indexOf("C_"),desc.length());
            } /* if */
         comm = comm + "            " + desc + ",\n";
         } /* if */

      // add end of UDT
      comm = comm.substring(0,comm.length()-2) +  " )" + abstr + "INSTANTIABLE NOT FINAL";

      // execute the query
      if (!dbc.executeUpdate(comm))
         {
         return false;
         } /* if */
      addToScript(comm);

      // add name of UDT do DOM graph
      pad.setUdtName(udt,udtID);
      nodes.add(pad);

      return true;
      } /* processComplexType */

   /***************************************************************************/
   /** Processing group DOM graph node. Returns true if succeded, else
    *  returns false. */
   private boolean processGroup
      (
      Node n,       /** actually processed node */
      AddData pad,  /** parent node additional data */
      DBCall dbc    /** class for comunication with DB */
      )
      {
      // process subelement
      NodeImpl ni_att = (NodeImpl)getAttribute(n,"ref");
      if (ni_att != null)     // attributeGroup is referenced
         {
         AddData ad_att = (AddData)ni_att.getUserData();
         Node      glob = ad_att.getGlobalNode();

         NodeImpl ni_gl = (NodeImpl)glob;
         AddData  ad_gl = (AddData)ni_gl.getUserData();
         if (!processDOMnode(glob,ad_gl,dbc,UN))
            {
            return false;
            } /* if */
         pad.copyContent(ad_gl);
         }
      else                    // process content
         {
         for (Node child = n.getFirstChild(); child != null; child = child.getNextSibling())
            {
            if (child.getNodeType() == Node.ELEMENT_NODE)
               {
               if (!processDOMnode(child,pad,dbc,UN))
                  {
                  return false;
                  } /* if */
               } /* if */
            } /* for */
         } /* else */

      // process attributes
      pad.setMinOccurs(getAttributeValue(n,"minOccurs"),true);
      pad.setMaxOccurs(getAttributeValue(n,"maxOccurs"),true);

      return true;
      } /* processGroup */

   /***************************************************************************/
   /** Processing all or sequence DOM graph node. Returns true if succeded, else
    *  returns false. */
   private boolean processGroupContent // all, choice or sequence
      (
      Node n,              /** actually processed node */
      AddData pad,         /** parent node additional data */
      DBCall dbc,          /** class for comunication with DB */
      char which           /** A = all, S = sequence, C = choice */
      )
      {
      AddData ch_ad;
      String comm, udt, desc;

      // process content child nodes
      for (Node child = n.getFirstChild(); child != null; child = child.getNextSibling())
         {
         if (child.getNodeType() != Node.ELEMENT_NODE)
            {
            continue;
            } /* if */

         NodeImpl ni_att = (NodeImpl)getAttribute(child,"ref");
         if (ni_att != null)   // globally defined node
            {
            AddData ad_att = (AddData)ni_att.getUserData();
            Node      glob = ad_att.getGlobalNode();

            NodeImpl ni_gl = (NodeImpl)glob;
            AddData  ad_gl = (AddData)ni_gl.getUserData();
            if (!processDOMnode(glob,ad_gl,dbc,UN))
               {
               return false;
               } /* if */
            ch_ad = ad_gl;

            ch_ad.setMinOccurs(getAttributeValue(child,"minOccurs"),false);
            ch_ad.setMaxOccurs(getAttributeValue(child,"maxOccurs"),false);
            }
         else                  // locally defined node
            {
            NodeImpl cni = (NodeImpl)child;
            ch_ad = (AddData)cni.getUserData();
            if (!processDOMnode(child,ch_ad,dbc,UN))
               {
               return false;
               } /* if */

            // child nodes of choice which aren't global are almost global
            if (which == 'C')
               {
               ch_ad.setAlmGlobal(true);
               } /* if */
            } /* else */

         pad.addElemList(ch_ad);
         } /* for */

      // generate UDT ID
      int udtID = dbc.getNEXTVAL("udtSeq");
      if (udtID == -1)
         {
         return false;
         } /* if */
      udt = "P_" + xmlSchemaID + "_" + udtID;

      // set map and element type
      pad.setComplexMapType();
      pad.setElemType(which);

      // create beginning of UDT
      comm = "CREATE TYPE " + udt + " UNDER xmlAncestor_" + xmlSchemaID + " (\n";

      ArrayList al = pad.getElemList();

      // add contents to UDT
      if (which == 'C')            // choice node
         {
         comm = comm + "            C_1 REF xmlAncestor_" + xmlSchemaID + ",\n";
         for (int i=0; i<al.size(); i++)
            {
            AddData ad = (AddData)al.get(i);
            ad.setOrder(1);
            } /* for */
         }
      else                         // all or sequence node
         {
         int order = 1;

         for (int i=0; i<al.size(); i++)
            {
            AddData ad = (AddData)al.get(i);

            // set order
            ad.setOrder(order++);

            // get description if simple type
            desc = ad.getComplexTypeDesc(udt,"C_");

            // check whether the string contains auxiliary command
            if (desc.indexOf("C_") != 0)
               {
               String aux = desc.substring(0,desc.indexOf("C_"));
               // process auxiliary command
               if (!dbc.executeUpdate(aux))
                  {
                  return false;
                  } /* if */
               addToScript(aux);

               // clear auxiliary command from the string
               desc = desc.substring(desc.indexOf("C_"),desc.length());
               } /* if */
            comm = comm + "            " + desc + ",\n";

            // if all node add order column
            if (which == 'A')
               {
               comm = comm + "            O_" + (order-1) + " INT,\n";
               } /* if */
            } /* for */
         } /* else */

      // add end of UDT
      comm = comm.substring(0,comm.length()-2) +  " ) INSTANTIABLE NOT FINAL";

      // execute the query
      if (!dbc.executeUpdate(comm))
         {
         return false;
         } /* if */
      addToScript(comm);

      // add name of UDT do DOM graph
      pad.setUdtName(udt,udtID);
      nodes.add(pad);

      // generate element ID
      int elemID = dbc.getNEXTVAL("elemSeq");
      if (elemID == -1)
         {
         return false;
         } /* if */

      // store record into xmlElemTable
      comm = "INSERT INTO xmlElemTable \n" +
             "   VALUES (" + xmlSchemaID + ", " +
                             elemID + ", " +
                             "null, '" +
                             pad.getMapType() + "', '" +
                             pad.getElemType() + "', '" +
                             udt + "')";
      if (!dbc.executeUpdate(comm))
         {
         errMes = "Error while processing SQL statement.";
         return false;
         } /* if */
      addToScript(comm);

      // store records into xmlElemElemTable
      for (int i=0; i<al.size(); i++)
         {
         AddData ad = (AddData)al.get(i);

         comm = "INSERT INTO xmlElemElemTable \n" +
                "   VALUES (" + xmlSchemaID + ", " +
                                elemID + ", " +
                                ad.getElemID() + ", " +
                                ad.getOrder() + ", '" +
                                ad.getColumnMap() + "', " +
                                ad.getMinOccurs() + ", " +
                                ad.getMaxOccurs() + ")";
         if (!dbc.executeUpdate(comm))
            {
            errMes = "Error while processing SQL statement.";
            return false;
            } /* if */
         addToScript(comm);
         } /* for */
      addToScript("");

      // add name of UDT do DOM graph
      pad.setElemID(elemID);

      // process attributes
      pad.setMinOccurs(getAttributeValue(n,"minOccurs"),false);
      pad.setMaxOccurs(getAttributeValue(n,"maxOccurs"),false);

      return true;
      } /* processGroupContent */

   /***************************************************************************/
   /** Processing element DOM graph node. Returns true if succeded, else
    *  returns false. */
   private boolean processElement
      (
      Node n,       /** actually processed node */
      AddData pad,  /** parent node additional data */
      DBCall dbc    /** class for comunication with DB */
      )
      {
      String udt;
      AddData type = null;

      // is it ref
      NodeImpl ni_att = (NodeImpl)getAttribute(n,"ref");
      if (ni_att != null)
         {
         AddData ad_att = (AddData)ni_att.getUserData();
         Node      glob = ad_att.getGlobalNode();

         NodeImpl ni_gl = (NodeImpl)glob;
         AddData  ad_gl = (AddData)ni_gl.getUserData();
         if (!processDOMnode(glob,ad_gl,dbc,UN))
            {
            return false;
            } /* if */
         pad.copyContent(ad_gl);
         return true;
         } /* if */

      // process attributes
      pad.setMinOccurs(getAttributeValue(n,"minOccurs"),false);
      pad.setMaxOccurs(getAttributeValue(n,"maxOccurs"),false);

      // is type specified by type?
      ni_att = (NodeImpl)getAttribute(n,"type");
      if (ni_att != null)     // simple type is specified by attribute type
         {
         AddData ad_att = (AddData)ni_att.getUserData();
         Node      glob = ad_att.getGlobalNode();

         if (glob != null)    // globaly defined type
            {
            NodeImpl ni_gl = (NodeImpl)glob;
            AddData  ad_gl = (AddData)ni_gl.getUserData();
            if (!processDOMnode(glob,ad_gl,dbc,UN))
               {
               return false;
               } /* if */
            pad.copyContent(ad_gl);
            type = ad_gl;
            }
         else                 // built-in type
            {
            pad.setSimpleType(getAttributeValue(n,"type"),prefix);
            } /* else */
         }
      else                    // defined by subelement
         {
         for (Node child = n.getFirstChild(); child != null; child = child.getNextSibling())
            {
            if (!processDOMnode(child,pad,dbc,UN))
               {
               return false;
               } /* if */
            } /* for */
         } /* else */

      // process attributes
      pad.setFixed(getAttributeValue(n,"fixed"));
      pad.setUse(getAttributeValue(n,"nillable"));

      // create udt, if wasn't yet
      udt = pad.getUdtName();
      if (udt == null)
         {
         int udtID = dbc.getNEXTVAL("udtSeq");
         if (udtID == -1)
            {
            return false;
            } /* if */
         udt = "E_" + xmlSchemaID + "_" + udtID;
         pad.setOrder(1);

         // create udt
         String comm = "CREATE TYPE " + udt + " UNDER xmlAncestor_" + xmlSchemaID + " (\n";
         String desc = pad.getSimpleTypeDesc(udt,"C_");

         // check whether the string contains auxiliary command
         if (desc.indexOf("C_") != 0)
            {
            String aux = desc.substring(0,desc.indexOf("C_"));
            if (!dbc.executeUpdate(aux))
               {
               return false;
               } /* if */
            addToScript(aux);

            // clear auxiliary command from the string
            desc = desc.substring(desc.indexOf("C_"),desc.length());
            } /* if */
         comm = comm + "            " + desc + ",\n";

         // add end of UDT
         comm = comm.substring(0,comm.length()-2) +  " ) INSTANTIABLE NOT FINAL";

         // execute the query
         if (!dbc.executeUpdate(comm))
            {
            return false;
            } /* if */
         addToScript(comm);

         // store into DOM graph
         pad.setUdtName(udt,udtID);
         nodes.add(pad);
         } /* if */

      // generate element ID
      int elemID = pad.getElemID();
      if (elemID == -1)
         {
         elemID = dbc.getNEXTVAL("elemSeq");
         if (elemID == -1)
            {
            return false;
            } /* if */
         } /* if */

      pad.setElemType(AddData.el);

      if ((type != null) && (!type.isProcessed()))
         {
         undefNodes.add(n);
         }
      else
         {
         // store record into xmlElemTable
         String comm = "INSERT INTO xmlElemTable \n" +
                       "   VALUES (" + xmlSchemaID + ", " +
                                elemID + ", '" +
                                getAttributeValue(n,"name") + "', '" +
                                pad.getMapType() + "', '" +
                                pad.getElemType() + "', '" +
                                udt + "')";
         if (!dbc.executeUpdate(comm))
            {
            errMes = "Error while processing SQL statement.";
            return false;
            } /* if */
         addToScript(comm);

         // store records into xmlElemAttrTable
         ArrayList al = pad.getAttrList();
         for (int i=0; i<al.size(); i++)
            {
            AddData ad = (AddData)al.get(i);

            comm = "INSERT INTO xmlElemAttrTable \n" +
                   "   VALUES (" + xmlSchemaID + ", " +
                                   elemID + ", " +
                                   ad.getAttrID() + ", " +
                                   ad.getOrder() + ")";
            if (!dbc.executeUpdate(comm))
               {
               errMes = "Error while processing SQL statement.";
               return false;
               } /* if */
            addToScript(comm);
            } /* for */

         // store records into xmlElemElemTable
         al = pad.getElemList();
         for (int i=0; i<al.size(); i++)
            {
            AddData ad = (AddData)al.get(i);

            comm = "INSERT INTO xmlElemElemTable \n" +
                   "   VALUES (" + xmlSchemaID + ", " +
                                   elemID + ", " +
                                   ad.getElemID() + ", " +
                                   ad.getOrder() + ", '" +
                                   ad.getColumnMap() + "', " +
                                   ad.getMinOccurs() + ", " +
                                   ad.getMaxOccurs() + ")";
            if (!dbc.executeUpdate(comm))
               {
               errMes = "Error while processing SQL statement.";
               return false;
               } /* if */
            addToScript(comm);
            } /* for */
         addToScript("");
         } /* else */

      // store elemID
      pad.setElemID(elemID);

      return true;
      } /* processElement */

   /***************************************************************************/
   /** Processing schema DOM graph node. Returns true if succeded, else
    *  returns false. */
   private boolean processSchema
      (
      Node n,              /** actually processed node */
      AddData pad,  /** parent node additional data */
      DBCall dbc           /** class for comunication with DB */
      )
      {
      NodeImpl cni;
      AddData cad;

      // set global variable to global elements and attributes
      for (Node child = n.getFirstChild(); child != null; child = child.getNextSibling())
         {
         if (child.getNodeType() == Node.ELEMENT_NODE)
            {
            cni = (NodeImpl)child;
            cad = (AddData)cni.getUserData();
            if (cad == null)
               {
               continue;
               } /* if */

            cad.setGlobal(true);

            if (child.getLocalName().equals("element") || child.getLocalName().equals("group"))
               {
               cad.setCreateTable(true);
               } /* if */
            } /* if */
         } /* for */

      // process child nodes
      for (Node child = n.getFirstChild(); child != null; child = child.getNextSibling())
         {
         if (child.getNodeType() == Node.ELEMENT_NODE)
            {
            cni = (NodeImpl)child;
            cad = (AddData)cni.getUserData();
            if (!processDOMnode(child,cad,dbc,SPEC))   // process special nodes
               {
               return false;
               } /* if */

            if (child.getLocalName().equals("element"))
               {
               cad.setOrder(1);
               pad.addElemList(cad);
               } /* if */
            } /* if */
         } /* for */

      // always create table for schema element
      pad.setCreateTable(true);

      // create udt id
      int udtID = dbc.getNEXTVAL("udtSeq");
      if (udtID == -1)
         {
         return false;
         } /* if */
      String udt = udt = "P_" + xmlSchemaID + "_" + udtID;

      // create UDT
      String comm = "CREATE TYPE " + udt + " UNDER xmlAncestor_" + xmlSchemaID + " (\n" +
                    "            C_1 REF xmlAncestor_" + xmlSchemaID + " ) INSTANTIABLE NOT FINAL";

      // execute the query
      if (!dbc.executeUpdate(comm))
         {
         return false;
         } /* if */
      addToScript(comm);

      // add name of UDT do DOM graph
      pad.setUdtName(udt,udtID);
      nodes.add(pad);
      pad.setComplexMapType();
      pad.setElemType(AddData.ch);

      // generate element ID
      int elemID = dbc.getNEXTVAL("elemSeq");
      if (elemID == -1)
         {
         return false;
         } /* if */

      // store record into xmlElemTable
      comm = "INSERT INTO xmlElemTable \n" +
             "   VALUES (" + xmlSchemaID + ", " +
                             elemID + ", " +
                             "null, '" +
                             pad.getMapType() + "', '" +
                             pad.getElemType() + "', '" +
                             udt + "')";
      if (!dbc.executeUpdate(comm))
         {
         errMes = "Error while processing SQL statement.";
         return false;
         } /* if */
      addToScript(comm);

      // store records into xmlElemElemTable
      ArrayList al = pad.getElemList();
      for (int i=0; i<al.size(); i++)
         {
         AddData ad = (AddData)al.get(i);

         comm = "INSERT INTO xmlElemElemTable \n" +
                "   VALUES (" + xmlSchemaID + ", " +
                                elemID + ", " +
                                ad.getElemID() + ", " +
                                ad.getOrder() + ", " +
                                "'R', " +
                                ad.getMinOccurs() + ", " +
                                ad.getMaxOccurs() + ")";
         if (!dbc.executeUpdate(comm))
            {
            errMes = "Error while processing SQL statement.";
            return false;
            } /* if */
         addToScript(comm);
         } /* for */

      // store a record into xmlSchemaTable
      comm = "UPDATE xmlSchemaTable SET rootElemID = " + elemID + "\n" +
             "   WHERE schemaID = " + xmlSchemaID;
      if (!dbc.executeUpdate(comm))
         {
         errMes = "Error while processing SQL statement.";
         return false;
         } /* if */
      addToScript(comm);
      addToScript("");

      return true;
      } /* processSchema */

   /***************************************************************************/
   /** Processing one DOM graph node. Returns true if succeded, else returns
    *  false. */
   private boolean processDOMnode
      (
      Node n,              /** actually rocessed node */
      AddData pad,         /** parent node additional data */
      DBCall dbc,          /** class for comunication with DB */
      int par              /** parrent node type */
      )
      {
      NodeImpl ni = (NodeImpl)n;
      AddData ad = (AddData)ni.getUserData();

      // check whether it is interesting node (it's UserData variable was set)
      if (ad == null)
         {
         return true;
         } /* if */

      // check whether the node is processed
      if (ad.isProcessed())
         {
         return true;
         } /* if */

      // check whether the node is special and shouldn't be processed
      if (ad.isSpecial() && (par != SPEC))
         {
         return true;
         } /* if */

      // get name of current node
      String nodeName = n.getLocalName();

      // process node according to it's kind
      if (n.getNodeType() == Node.DOCUMENT_NODE)       // document node
         {
         // process the only interesting child node -- schema
         Node child = getSubelement(n,"schema");
         if (child != null)
            {
            if (!processDOMnode(child,null,dbc,UN))
               {
               return false;
               } /* if */
            } /* if */
         }
      else                                             // elements
         {
         if (nodeName.equals("minExclusive"))
            {
            pad.setMinExclusive(getAttributeValue(n,"value"));
            }
         else if (nodeName.equals("minInclusive"))
            {
            pad.setMinInclusive(getAttributeValue(n,"value"));
            }
         else if (nodeName.equals("maxExclusive"))
            {
            pad.setMaxExclusive(getAttributeValue(n,"value"));
            }
         else if (nodeName.equals("maxInclusive"))
            {
            pad.setMaxInclusive(getAttributeValue(n,"value"));
            }
         else if (nodeName.equals("totalDigits"))
            {
            pad.setTotalDigits(getAttributeValue(n,"value"));
            }
         else if (nodeName.equals("fractionDigits"))
            {
            pad.setFractionDigits(getAttributeValue(n,"value"));
            }
         else if (nodeName.equals("length"))
            {
            pad.setLength(getAttributeValue(n,"value"));
            }
         else if (nodeName.equals("minLength"))
            {
            pad.setMinLength(getAttributeValue(n,"value"));
            }
         else if (nodeName.equals("maxLength"))
            {
            pad.setMaxLength(getAttributeValue(n,"value"));
            }
         else if (nodeName.equals("enumeration"))
            {
            pad.setEnumeration(getAttributeValue(n,"value"));
            }
         else if (nodeName.equals("union"))
            {
            pad.setUnion();
            }
         else if (nodeName.equals("list"))
            {
            if (!processList(n,pad,dbc))
               {
               return false;
               } /* if */
            }
         else if (nodeName.equals("simpleType"))
            {
            for (Node child = n.getFirstChild(); child != null; child = child.getNextSibling())
               {
               if (!processDOMnode(child,pad,dbc,ST))
                  {
                  return false;
                  } /* if */
               } /* for */
            }
         else if (nodeName.equals("attribute"))
            {
            if (!processAttribute(n,pad,dbc))
               {
               return false;
               } /* if */
            }
         else if (nodeName.equals("attributeGroup"))
            {
            if (!processAttributeGroup(n,pad,dbc))
               {
               return false;
               } /* if */
            }
         else if (nodeName.equals("restriction"))
            {
            // process according to parrent node type
            switch (par)
               {
               case ST : // simpleType
                         if (!processSTrestriction(n,pad,dbc))
                            {
                            return false;
                            } /* if */
                         break;
               case SC : // simpleContent
                         if (!processSCrestriction(n,pad,dbc))
                            {
                            return false;
                            } /* if */
                         break;
               } /* switch */
            }
         else if (nodeName.equals("extension"))
            {
            // process according to parrent node type
            switch (par)
               {
               case SC : // simpleContent
                         if (!processSCextension(n,pad,dbc))
                            {
                            return false;
                            } /* if */
                         break;
               } /* switch */
            }
         else if (nodeName.equals("simpleContent"))
            {
            for (Node child = n.getFirstChild(); child != null; child = child.getNextSibling())
               {
               if (!processDOMnode(child,pad,dbc,SC))
                  {
                  return false;
                  } /* if */
               } /* for */
            }
         else if (nodeName.equals("complexType"))
            {
            if (!processComplexType(n,pad,dbc))
               {
               return false;
               } /* if */
            }
         else if (nodeName.equals("group"))
            {
            if (!processGroup(n,pad,dbc))
               {
               return false;
               } /* if */
            }
         else if (nodeName.equals("all"))
            {
            if (!processGroupContent(n,pad,dbc,AddData.al))
               {
               return false;
               } /* if */
            }
         else if (nodeName.equals("choice"))
            {
            if (!processGroupContent(n,pad,dbc,AddData.ch))
               {
               return false;
               } /* if */
            }
         else if (nodeName.equals("sequence"))
            {
            if (!processGroupContent(n,pad,dbc,AddData.se))
               {
               return false;
               } /* if */
            }
         else if (nodeName.equals("element"))
            {
            if (!processElement(n,pad,dbc))
               {
               return false;
               } /* if */
            }
         else if (nodeName.equals("schema"))
            {
            if (!processSchema(n,(AddData)((NodeImpl)n).getUserData(),dbc))
               {
               return false;
               } /* if */
            }
         else // whiteSpace, pattern
            {
            // whiteSpace - not interesting
            // pattern    - requires SIMILAR TO predicate, which is not
            //              supported in Oracle9i
            // complexContent - processed in processComplexType() function
            // complexContent restriction - processed in processComplexContent() function
            // complexContent extension - processed in processComplexContent() function
            } /* if */
         } /* else */

      // node is processed
      ad.setProcessed(true);

      return true;
      } /* processDOMnode */

   /***************************************************************************/
   /** Processing of the DOM graph. Creating object-relational schema.
    *  Returns true if succeded, else returns false. */
   private boolean phase3_processDOMgraph
      (
      DBCall dbc     /** class for comunication with DB */
      )
      {
      // clear list of nodes whose table should be created
      nodes.clear();

      // set all nodes to not processed
      NodeImpl sch = (NodeImpl)doc.getFirstChild();
      setProcessed(sch,false);

      // recoursively process DOM graph nodes
      if (!processDOMnode(doc,null,dbc,UN))
         {
         return false;
         } /* if */

      return true;
      } /* phase3_processDOMgraph */

   /***************************************************************************/
   /******************************* PHASE IV. *********************************/
   /***************************************************************************/

   /***************************************************************************/
   /** Completation of incomplete elements.
    *  Returns true if succeded, else returns false. */
   private boolean completeElements
      (
      DBCall dbc     /** class for comunication with DB */
      )
      {
      for (int j=0; j<undefNodes.size(); j++)
         {
         // parameters for element
         Node n = (Node)undefNodes.get(j);
         NodeImpl ni = (NodeImpl)n;
         AddData pad = (AddData)ni.getUserData();

         // parameters for attribute type
         NodeImpl ni_att = (NodeImpl)getAttribute(n,"type");
         AddData ad_att = (AddData)ni_att.getUserData();

         // parameters for global node
         Node glob = ad_att.getGlobalNode();
         NodeImpl ni_gl = (NodeImpl)glob;
         AddData  ad_gl = (AddData)ni_gl.getUserData();

         // store record into xmlElemTable
         String comm = "INSERT INTO xmlElemTable \n" +
                       "   VALUES (" + xmlSchemaID + ", " +
                                pad.getElemID() + ", '" +
                                getAttributeValue(n,"name") + "', " +
                                "'C', " +
                                "'E', '" +
                                pad.getUdtName() + "')";
         if (!dbc.executeUpdate(comm))
            {
            errMes = "Error while processing SQL statement.";
            return false;
            } /* if */
         addToScript(comm);

         // store records into xmlElemAttrTable
         ArrayList al = ad_gl.getAttrList();
         for (int i=0; i<al.size(); i++)
            {
            AddData ad = (AddData)al.get(i);

            comm = "INSERT INTO xmlElemAttrTable \n" +
                   "   VALUES (" + xmlSchemaID + ", " +
                                   pad.getElemID() + ", " +
                                   ad.getAttrID() + ", " +
                                   ad.getOrder() + ")";
            if (!dbc.executeUpdate(comm))
               {
               errMes = "Error while processing SQL statement.";
               return false;
               } /* if */
            addToScript(comm);
            } /* for */

         // store records into xmlElemElemTable
         al = ad_gl.getElemList();
         for (int i=0; i<al.size(); i++)
            {
            AddData ad = (AddData)al.get(i);

            comm = "INSERT INTO xmlElemElemTable \n" +
                   "   VALUES (" + xmlSchemaID + ", " +
                                   pad.getElemID() + ", " +
                                   ad.getElemID() + ", " +
                                   ad.getOrder() + ", '" +
                                   ad.getColumnMap() + "', " +
                                   ad.getMinOccurs() + ", " +
                                   ad.getMaxOccurs() + ")";
            if (!dbc.executeUpdate(comm))
               {
               errMes = "Error while processing SQL statement.";
               return false;
               } /* if */
            addToScript(comm);
            } /* for */
         addToScript("");
         } /* for */

      return true;
      } /* completeElements */

   /***************************************************************************/
   /** Recoursive creates tables of the schema */
   private void getConstraints
      (
      Node n,         /** actually processed node */
      ArrayList li,   /** list for constraints */
      String prefix   /** prefix for column names */
      )
      {
      NodeImpl ni = (NodeImpl)n;
      AddData  ad = (AddData)ni.getUserData();
      String pre;
      String cons;

      if (prefix.equals(""))    // no prefix
         {
         pre = "";
         }
      else                      // add connection
         {
         pre = prefix + "." ;
         } /* else */

      // process constraints for attributes, if any
      ArrayList al = ad.getAttrList();
      for (int i=0; i<al.size(); i++)
         {
         AddData a = (AddData)al.get(i);
         cons = a.getSimpleTypeConstr(pre + "A_");
         if (!cons.equals(""))
            {
            li.add(cons);
            } /* if */
         } /* for */

      // no constrains for choice
      if (ad.getElemType() == AddData.ch)
         {
         return;
         } /* if */

      // simple type content
      if (ad.getMapType() != 'C')
         {
         ad.setOrder(al.size() + 1);
         cons = ad.getSimpleTypeConstr(pre + "C_");
         if (!cons.equals(""))
            {
            li.add(cons);
            } /* if */
         return;
         } /* if */

      // recoursively process interesting subelements, if any
      al = ad.getElemList();
      for (int i=0; i<al.size(); i++)
         {
         AddData a = (AddData)al.get(i);

         // mapped on collumn - process recoursively
         if (a.getColumnMap() == AddData.col)
            {
            String pr = new String(pre + "C_" + a.getOrder());
            getConstraints(a.getMyNode(),li,pr);
            pr = null;
            } /* if */

         cons = a.getComplexTypeConstr(pre + "C_");
         if (!cons.equals(""))
            {
            li.add(cons);
            } /* if */
         } /* for */
      } /* getConstraints */

   /***************************************************************************/
   /** Recoursive creates tables of the schema */
   private boolean createTables
      (
      DBCall dbc     /** class for comunication with DB */
      )
      {
      for (int j=0; j<nodes.size(); j++)
         {
         AddData ad = (AddData)nodes.get(j);

         // check whether the table was created
         if (ad.isCreated() > 0)
            {
            return true;
            } /* if */

         // ceate table if necessary
         if (ad.getCreateTable())
            {
            // create table
            String comm = "CREATE TABLE T_" + ad.getUdtName() + " OF " + ad.getUdtName();
            if (!dbc.executeUpdate(comm))
               {
               errMes = "Error while processing SQL statement.";
               return false;
               } /* if */
            addToScript(comm);
            } /* if */

         // set processed
         ad.setCreated(1);
         } /* for */

      return true;
      } /* createTables */

   /***************************************************************************/
   /** Recoursive creates tables of the schema */
   private boolean createConstraints
      (
      DBCall dbc     /** class for comunication with DB */
      )
      {
      for (int j=0; j<nodes.size(); j++)
         {
         AddData ad = (AddData)nodes.get(j);

         // check whether the table was created
         if (ad.isCreated() > 1)
            {
            return true;
            } /* if */

         // ceate table if necessary
         if (ad.getCreateTable())
            {
            // recoursively collect and add constraints
            ArrayList co = new ArrayList();
            getConstraints(ad.getMyNode(),co,"");
            for (int i=0; i<co.size(); i++)
               {
               String c = (String)co.get(i);

               String comm = "ALTER TABLE T_" + ad.getUdtName() + " ADD (" + c + ")";
               if (dbc.executeUpdate(comm))
                  {
                  addToScript(comm);
                  } /* if */
               } /* for */
            } /* if */

         // set processed
         ad.setCreated(2);
         } /* for */

      return true;
      } /* createConstraints */

   /***************************************************************************/
   /** Sets processed variable. */
   private void setProcessed
      (
      Node n,      /** actually rocessed node */
      boolean pr   /** new value */
      )
      {
      NodeImpl ni = (NodeImpl)n;
      AddData  ad = (AddData)ni.getUserData();

      // am I interesting node?
      if (ni.getUserData() == null)
         {
         return;
         } /* if */

      if (ad.isProcessed() == pr)
         {
         return;
         } /* if */

      // set processed
      ad.setProcessed(pr);

      // process attributes if any
      if (n.getNodeType() == Node.ELEMENT_NODE)
         {
         NamedNodeMap atts = n.getAttributes();
         for (int i = 0; i < atts.getLength(); i++)
            {
            Node att = atts.item(i);
            setProcessed(att,pr);
            } /* for */
         } /* if */

      // process children elements if any
      for (Node child = n.getFirstChild(); child != null; child = child.getNextSibling())
         {
         setProcessed(child,pr);
         } /* for */

      // process global node, if any
      Node glob = ad.getGlobalNode();
      if (glob != null)
         {
         setProcessed(glob,pr);
         } /* if */
      } /* setProcessed */

   /***************************************************************************/
   /** Recoursive creates tables of the schema */
   private boolean fillXmlPathTable
      (
      DBCall dbc,    /** class for comunication with DB */
      Node n,        /** actually processed node */
      int prevID,    /** id of parrent element */
      String list    /** comma separated list of IDs */
      )
      {
      NodeImpl ni = (NodeImpl)n;
      AddData  ad = (AddData)ni.getUserData();
      int currID;

      // am I interesting node?
      if (ad == null)
         {
         return true;
         } /* if */

      // am I element schema?
      if (n.getLocalName().equals("schema"))
         {
         // process children elements, if any
         for (Node child = n.getFirstChild(); child != null; child = child.getNextSibling())
            {
            NodeImpl ci = (NodeImpl)child;
            AddData  ca = (AddData)ci.getUserData();
            if (ca == null)
               {
               continue;
               } /* if */

            if (ca.getElemType() == 'E')
               {
               String  str = new String("");
               if (!fillXmlPathTable(dbc,child,ca.getElemID(),str))
                  {
                  return false;
                  } /* if */
               str = null;
               } /* if */
            } /* for */

         ad.setProcessed(true);
         return true;
         } /* if */

      // if it is element, store recort into table
      if ( (ad.getElemType() == 'E') &&
           ((prevID != ad.getElemID()) || ((prevID == ad.getElemID()) && (!list.equals("")))) )
         {
         String comm = "INSERT INTO xmlPathTable VALUES (\n" +
                       "   " + xmlSchemaID + ", " + prevID + ", " + ad.getElemID() + ", " +
                       "arrayOfIDs(" + list.substring(0,list.length()-2) + "))";
         if (!dbc.executeUpdate(comm))
            {
            errMes = "Error while processing SQL statement.";
            return false;
            } /* if */
         addToScript(comm);

         currID = ad.getElemID();
         }
      else
         {
         currID = prevID;
         } /* else */

      // do not go on in recoursion for processed elements
      if ((ad.getElemType() == 'E') && ad.isProcessed())
         {
         return true;
         } /* if */

      // set processed
      ad.setProcessed(true);

      // process children elements, if any
      for (Node child = n.getFirstChild(); child != null; child = child.getNextSibling())
         {
         String str;

         if (ad.getElemType() == 'E')
            {
            str = new String("");
            }
         else if ((ad.getElemType() == 'A') || (ad.getElemType() == 'C') || (ad.getElemType() == 'S'))
            {
            str = new String(list + ad.getElemID() + ", ");
            }
         else
            {
            str = new String(list);
            } /* else */

         if (!fillXmlPathTable(dbc,child,currID,str))
            {
            return false;
            } /* if */
         str = null;
         } /* for */

      // process attributes with global node, if any
      if (n.getNodeType() == Node.ELEMENT_NODE)
         {
         NamedNodeMap atts = n.getAttributes();
         for (int i = 0; i < atts.getLength(); i++)
            {
            Node    att = atts.item(i);
            NodeImpl ci = (NodeImpl)att;
            AddData  ca = (AddData)ci.getUserData();
            if (ca == null)
               {
               continue;
               } /* if */

            Node glob = ca.getGlobalNode();
            if (glob != null)
               {
               String str;

               if (ad.getElemType() == 'E')
                  {
                  str = new String("");
                  }
               else if ((ad.getElemType() == 'A') || (ad.getElemType() == 'C') || (ad.getElemType() == 'S'))
                  {
                  str = new String(list + ad.getElemID() + ", ");
                  }
               else
                  {
                  str = new String(list);
                  } /* else */

               if (!fillXmlPathTable(dbc,glob,currID,str))
                  {
                  return false;
                  } /* if */
               str = null;
               } /* if */
            } /* for */
         } /* if */

      return true;
      } /* fillXmlPathTable */

   /***************************************************************************/
   /** Recoursive removing of AddData class from DOM graph. */
   private void deleteAditionalData
      (
      Node n      /** actually rocessed node */
      )
      {
      NodeImpl ni = (NodeImpl)n;

      // process attributes if any
      if (n.getNodeType() == Node.ELEMENT_NODE)
         {
         NamedNodeMap atts = n.getAttributes();
         for (int i = 0; i < atts.getLength(); i++)
            {
            Node att = atts.item(i);
            deleteAditionalData(att);
            } /* for */
         } /* if */

      // process children elements if any
      for (Node child = n.getFirstChild(); child != null; child = child.getNextSibling())
         {
         deleteAditionalData(child);
         } /* for */

      // null user data
      ni.setUserData(null);
      } /* deleteAditionalData */

   /***************************************************************************/
   /** Creating schema tables, storing data into xmlPathTable table.
    *  Returns true if succeded, else returns false. */
   private boolean phase4_finalization
      (
      DBCall dbc     /** class for comunication with DB */
      )
      {
      // complete elements
      if (!completeElements(dbc))
         {
         return false;
         } /* if */

      // create tables
      if (!createTables(dbc))
         {
         return false;
         } /* if */
      addToScript("");

      // create constraints
      if (!createConstraints(dbc))
         {
         return false;
         } /* if */
      addToScript("");

      // fill xmlPathTable
      NodeImpl sch = (NodeImpl)doc.getFirstChild();
      setProcessed(sch,false);
      if (!fillXmlPathTable(dbc,sch,-1,null))
         {
         return false;
         } /* if */

      // smaz UserData z DOM stromu
      deleteAditionalData(doc);

      // zavri skript
      closeScript();

      return true;
      } /* phase4_finalization */

   /***************************************************************************/
   /*********************** OTHER PUBLIC FUNCTIONS ****************************/
   /***************************************************************************/

   /***************************************************************************/
   /** Tranforming the XML schema to object-relational schema. Returns true if the
    *  transformation succeded, else returns false. */
   public boolean processXMLSchema
      (
      File file,     /** XML schema file name */
      DBCall dbc     /** class for comunication with DB */
      )
      {
      schema = file;

      // prepare script for storing SQL commands
      prepareScript();

      // transform the schema
      if (!phase1_preparation(dbc))
         {
         closeScript();
         return false;
         } /* if */
      if (!phase2_createDOMgraph(dbc))
         {
         closeScript();
         return false;
         } /* if */
      if (!phase3_processDOMgraph(dbc))
         {
         closeScript();
         return false;
         } /* if */
      if (!phase4_finalization(dbc))
         {
         closeScript();
         return false;
         } /* if */

      // close created script
      closeScript();

      return true;
      } /* processXMLSchema */

   /***************************************************************************/
   /** Returns the name of the script for creating the object-relational schema. */
   public String getScriptName ()
      {
      return scrName;
      } /* getScriptName */

   /***************************************************************************/
   /** Returns the error message. */
   public String getErrMes ()
      {
      return errMes;
      } /* getScriptName */
   } /* class XMLSchema2DB */