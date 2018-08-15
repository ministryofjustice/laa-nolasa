
package uk.gov.justice._2013._11.magistrates;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for LibraOffenceType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="LibraOffenceType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="offenceCode">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;maxLength value="8"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="SeqNo" minOccurs="0">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;length value="3"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="OffenceShortTitle">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;maxLength value="120"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="OffenceClassification">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;maxLength value="250"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="OffenceDate" type="{http://www.w3.org/2001/XMLSchema}date" minOccurs="0"/>
 *         &lt;element name="OffenceWording" minOccurs="0">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;maxLength value="2500"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="ModeOfTrial" type="{http://www.w3.org/2001/XMLSchema}positiveInteger" minOccurs="0"/>
 *         &lt;element name="LegalaidReason" minOccurs="0">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;maxLength value="250"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="LegalaidStatus" minOccurs="0">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;enumeration value="AP"/>
 *               &lt;enumeration value="GR"/>
 *               &lt;enumeration value="FM"/>
 *               &lt;enumeration value="FJ"/>
 *               &lt;enumeration value="FB"/>
 *               &lt;enumeration value="WD"/>
 *               &lt;enumeration value="G2"/>
 *               &lt;enumeration value="GQ"/>
 *               &lt;enumeration value="RE"/>
 *               &lt;enumeration value="VA"/>
 *               &lt;enumeration value="WI"/>
 *               &lt;enumeration value="NA"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="LegalaidStatusDate" type="{http://www.w3.org/2001/XMLSchema}date" minOccurs="0"/>
 *         &lt;element name="RESULT" type="{http://www.justice.gov.uk/2013/11/magistrates}LibraResultType" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "LibraOffenceType", propOrder = {
    "offenceCode",
    "seqNo",
    "offenceShortTitle",
    "offenceClassification",
    "offenceDate",
    "offenceWording",
    "modeOfTrial",
    "legalaidReason",
    "legalaidStatus",
    "legalaidStatusDate",
    "result"
})
public class LibraOffenceType {

    @XmlElement(required = true)
    protected String offenceCode;
    @XmlElement(name = "SeqNo")
    protected String seqNo;
    @XmlElement(name = "OffenceShortTitle", required = true)
    protected String offenceShortTitle;
    @XmlElement(name = "OffenceClassification", required = true)
    protected String offenceClassification;
    @XmlElement(name = "OffenceDate")
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar offenceDate;
    @XmlElement(name = "OffenceWording")
    protected String offenceWording;
    @XmlElement(name = "ModeOfTrial")
    @XmlSchemaType(name = "positiveInteger")
    protected BigInteger modeOfTrial;
    @XmlElement(name = "LegalaidReason")
    protected String legalaidReason;
    @XmlElement(name = "LegalaidStatus")
    protected String legalaidStatus;
    @XmlElement(name = "LegalaidStatusDate")
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar legalaidStatusDate;
    @XmlElement(name = "RESULT")
    protected List<LibraResultType> result;

    /**
     * Gets the value of the offenceCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOffenceCode() {
        return offenceCode;
    }

    /**
     * Sets the value of the offenceCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOffenceCode(String value) {
        this.offenceCode = value;
    }

    /**
     * Gets the value of the seqNo property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSeqNo() {
        return seqNo;
    }

    /**
     * Sets the value of the seqNo property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSeqNo(String value) {
        this.seqNo = value;
    }

    /**
     * Gets the value of the offenceShortTitle property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOffenceShortTitle() {
        return offenceShortTitle;
    }

    /**
     * Sets the value of the offenceShortTitle property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOffenceShortTitle(String value) {
        this.offenceShortTitle = value;
    }

    /**
     * Gets the value of the offenceClassification property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOffenceClassification() {
        return offenceClassification;
    }

    /**
     * Sets the value of the offenceClassification property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOffenceClassification(String value) {
        this.offenceClassification = value;
    }

    /**
     * Gets the value of the offenceDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getOffenceDate() {
        return offenceDate;
    }

    /**
     * Sets the value of the offenceDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setOffenceDate(XMLGregorianCalendar value) {
        this.offenceDate = value;
    }

    /**
     * Gets the value of the offenceWording property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOffenceWording() {
        return offenceWording;
    }

    /**
     * Sets the value of the offenceWording property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOffenceWording(String value) {
        this.offenceWording = value;
    }

    /**
     * Gets the value of the modeOfTrial property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getModeOfTrial() {
        return modeOfTrial;
    }

    /**
     * Sets the value of the modeOfTrial property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setModeOfTrial(BigInteger value) {
        this.modeOfTrial = value;
    }

    /**
     * Gets the value of the legalaidReason property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLegalaidReason() {
        return legalaidReason;
    }

    /**
     * Sets the value of the legalaidReason property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLegalaidReason(String value) {
        this.legalaidReason = value;
    }

    /**
     * Gets the value of the legalaidStatus property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLegalaidStatus() {
        return legalaidStatus;
    }

    /**
     * Sets the value of the legalaidStatus property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLegalaidStatus(String value) {
        this.legalaidStatus = value;
    }

    /**
     * Gets the value of the legalaidStatusDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getLegalaidStatusDate() {
        return legalaidStatusDate;
    }

    /**
     * Sets the value of the legalaidStatusDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setLegalaidStatusDate(XMLGregorianCalendar value) {
        this.legalaidStatusDate = value;
    }

    /**
     * Gets the value of the result property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the result property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getRESULT().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link LibraResultType }
     * 
     * 
     */
    public List<LibraResultType> getRESULT() {
        if (result == null) {
            result = new ArrayList<LibraResultType>();
        }
        return this.result;
    }

}
