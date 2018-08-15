
package uk.gov.justice._2013._11.magistrates;

import java.math.BigInteger;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for LibraResultType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="LibraResultType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="ResultCode" type="{http://www.w3.org/2001/XMLSchema}positiveInteger"/>
 *         &lt;element name="ResultShortTitle">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;maxLength value="100"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="ResultText">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;maxLength value="2500"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="ResultQualifiers" minOccurs="0">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;maxLength value="40"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="NextHearingDate" type="{http://www.w3.org/2001/XMLSchema}date" minOccurs="0"/>
 *         &lt;element name="NextHearingLocation" minOccurs="0">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;length value="7"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="LegalaidFirmName" minOccurs="0">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;maxLength value="100"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="LegalaidContactName" minOccurs="0">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;maxLength value="35"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="LaaAccountNumber" minOccurs="0">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;maxLength value="35"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="LegalaidWithdrawalDate" type="{http://www.w3.org/2001/XMLSchema}date" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "LibraResultType", propOrder = {
    "resultCode",
    "resultShortTitle",
    "resultText",
    "resultQualifiers",
    "nextHearingDate",
    "nextHearingLocation",
    "legalaidFirmName",
    "legalaidContactName",
    "laaAccountNumber",
    "legalaidWithdrawalDate"
})
public class LibraResultType {

    @XmlElement(name = "ResultCode", required = true)
    @XmlSchemaType(name = "positiveInteger")
    protected BigInteger resultCode;
    @XmlElement(name = "ResultShortTitle", required = true)
    protected String resultShortTitle;
    @XmlElement(name = "ResultText", required = true)
    protected String resultText;
    @XmlElement(name = "ResultQualifiers")
    protected String resultQualifiers;
    @XmlElement(name = "NextHearingDate")
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar nextHearingDate;
    @XmlElement(name = "NextHearingLocation")
    protected String nextHearingLocation;
    @XmlElement(name = "LegalaidFirmName")
    protected String legalaidFirmName;
    @XmlElement(name = "LegalaidContactName")
    protected String legalaidContactName;
    @XmlElement(name = "LaaAccountNumber")
    protected String laaAccountNumber;
    @XmlElement(name = "LegalaidWithdrawalDate")
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar legalaidWithdrawalDate;

    /**
     * Gets the value of the resultCode property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getResultCode() {
        return resultCode;
    }

    /**
     * Sets the value of the resultCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setResultCode(BigInteger value) {
        this.resultCode = value;
    }

    /**
     * Gets the value of the resultShortTitle property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getResultShortTitle() {
        return resultShortTitle;
    }

    /**
     * Sets the value of the resultShortTitle property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setResultShortTitle(String value) {
        this.resultShortTitle = value;
    }

    /**
     * Gets the value of the resultText property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getResultText() {
        return resultText;
    }

    /**
     * Sets the value of the resultText property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setResultText(String value) {
        this.resultText = value;
    }

    /**
     * Gets the value of the resultQualifiers property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getResultQualifiers() {
        return resultQualifiers;
    }

    /**
     * Sets the value of the resultQualifiers property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setResultQualifiers(String value) {
        this.resultQualifiers = value;
    }

    /**
     * Gets the value of the nextHearingDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getNextHearingDate() {
        return nextHearingDate;
    }

    /**
     * Sets the value of the nextHearingDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setNextHearingDate(XMLGregorianCalendar value) {
        this.nextHearingDate = value;
    }

    /**
     * Gets the value of the nextHearingLocation property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNextHearingLocation() {
        return nextHearingLocation;
    }

    /**
     * Sets the value of the nextHearingLocation property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNextHearingLocation(String value) {
        this.nextHearingLocation = value;
    }

    /**
     * Gets the value of the legalaidFirmName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLegalaidFirmName() {
        return legalaidFirmName;
    }

    /**
     * Sets the value of the legalaidFirmName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLegalaidFirmName(String value) {
        this.legalaidFirmName = value;
    }

    /**
     * Gets the value of the legalaidContactName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLegalaidContactName() {
        return legalaidContactName;
    }

    /**
     * Sets the value of the legalaidContactName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLegalaidContactName(String value) {
        this.legalaidContactName = value;
    }

    /**
     * Gets the value of the laaAccountNumber property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLaaAccountNumber() {
        return laaAccountNumber;
    }

    /**
     * Sets the value of the laaAccountNumber property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLaaAccountNumber(String value) {
        this.laaAccountNumber = value;
    }

    /**
     * Gets the value of the legalaidWithdrawalDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getLegalaidWithdrawalDate() {
        return legalaidWithdrawalDate;
    }

    /**
     * Sets the value of the legalaidWithdrawalDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setLegalaidWithdrawalDate(XMLGregorianCalendar value) {
        this.legalaidWithdrawalDate = value;
    }

}
