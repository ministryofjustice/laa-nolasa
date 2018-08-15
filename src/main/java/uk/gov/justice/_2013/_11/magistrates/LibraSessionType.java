
package uk.gov.justice._2013._11.magistrates;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for LibraSessionType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="LibraSessionType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="SessionDateOfHearing" type="{http://www.w3.org/2001/XMLSchema}date"/>
 *         &lt;element name="SessionCourtLocation">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;maxLength value="7"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="SessionValidateDate" type="{http://www.w3.org/2001/XMLSchema}date" minOccurs="0"/>
 *         &lt;element name="DefendantCustodyStatus" minOccurs="0">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;maxLength value="1"/>
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
@XmlType(name = "LibraSessionType", propOrder = {
    "sessionDateOfHearing",
    "sessionCourtLocation",
    "sessionValidateDate",
    "defendantCustodyStatus"
})
public class LibraSessionType {

    @XmlElement(name = "SessionDateOfHearing", required = true)
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar sessionDateOfHearing;
    @XmlElement(name = "SessionCourtLocation", required = true)
    protected String sessionCourtLocation;
    @XmlElement(name = "SessionValidateDate")
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar sessionValidateDate;
    @XmlElement(name = "DefendantCustodyStatus")
    protected String defendantCustodyStatus;

    /**
     * Gets the value of the sessionDateOfHearing property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getSessionDateOfHearing() {
        return sessionDateOfHearing;
    }

    /**
     * Sets the value of the sessionDateOfHearing property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setSessionDateOfHearing(XMLGregorianCalendar value) {
        this.sessionDateOfHearing = value;
    }

    /**
     * Gets the value of the sessionCourtLocation property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSessionCourtLocation() {
        return sessionCourtLocation;
    }

    /**
     * Sets the value of the sessionCourtLocation property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSessionCourtLocation(String value) {
        this.sessionCourtLocation = value;
    }

    /**
     * Gets the value of the sessionValidateDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getSessionValidateDate() {
        return sessionValidateDate;
    }

    /**
     * Sets the value of the sessionValidateDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setSessionValidateDate(XMLGregorianCalendar value) {
        this.sessionValidateDate = value;
    }

    /**
     * Gets the value of the defendantCustodyStatus property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDefendantCustodyStatus() {
        return defendantCustodyStatus;
    }

    /**
     * Sets the value of the defendantCustodyStatus property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDefendantCustodyStatus(String value) {
        this.defendantCustodyStatus = value;
    }

}
