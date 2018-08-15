
package uk.gov.justice._2013._11.magistrates;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="AckId" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="Exception" type="{http://www.justice.gov.uk/2013/11/magistrates}LibraExceptionType"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "ackId",
    "exception"
})
@XmlRootElement(name = "AckResponse")
public class AckResponse {

    @XmlElement(name = "AckId", required = true)
    protected String ackId;
    @XmlElement(name = "Exception", required = true)
    protected LibraExceptionType exception;

    /**
     * Gets the value of the ackId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAckId() {
        return ackId;
    }

    /**
     * Sets the value of the ackId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAckId(String value) {
        this.ackId = value;
    }

    /**
     * Gets the value of the exception property.
     * 
     * @return
     *     possible object is
     *     {@link LibraExceptionType }
     *     
     */
    public LibraExceptionType getException() {
        return exception;
    }

    /**
     * Sets the value of the exception property.
     * 
     * @param value
     *     allowed object is
     *     {@link LibraExceptionType }
     *     
     */
    public void setException(LibraExceptionType value) {
        this.exception = value;
    }

}
