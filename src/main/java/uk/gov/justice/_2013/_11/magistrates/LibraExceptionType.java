
package uk.gov.justice._2013._11.magistrates;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for LibraExceptionType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="LibraExceptionType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="ERRORCODE">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;maxLength value="6"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="ERRORTIMESTAMP">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;maxLength value="20"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="ERRORMESSAGE">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;maxLength value="250"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "LibraExceptionType", propOrder = {
    "errorcode",
    "errortimestamp",
    "errormessage"
})
public class LibraExceptionType {

    @XmlElement(name = "ERRORCODE", required = true)
    protected String errorcode;
    @XmlElement(name = "ERRORTIMESTAMP", required = true)
    protected String errortimestamp;
    @XmlElement(name = "ERRORMESSAGE", required = true)
    protected String errormessage;

    /**
     * Gets the value of the errorcode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getERRORCODE() {
        return errorcode;
    }

    /**
     * Sets the value of the errorcode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setERRORCODE(String value) {
        this.errorcode = value;
    }

    /**
     * Gets the value of the errortimestamp property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getERRORTIMESTAMP() {
        return errortimestamp;
    }

    /**
     * Sets the value of the errortimestamp property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setERRORTIMESTAMP(String value) {
        this.errortimestamp = value;
    }

    /**
     * Gets the value of the errormessage property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getERRORMESSAGE() {
        return errormessage;
    }

    /**
     * Sets the value of the errormessage property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setERRORMESSAGE(String value) {
        this.errormessage = value;
    }

}
