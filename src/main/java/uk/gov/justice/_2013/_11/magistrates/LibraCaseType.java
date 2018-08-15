
package uk.gov.justice._2013._11.magistrates;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for LibraCaseType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="LibraCaseType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="LibraCaseId">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;length value="10"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="CJSAreaCode">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;length value="2"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="MaatCaseId" minOccurs="0">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;maxLength value="35"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="ASN" minOccurs="0">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;maxLength value="20"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="documentLanguage" minOccurs="0">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;enumeration value="EN"/>
 *               &lt;enumeration value="CY"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="Inactive">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;enumeration value="Y"/>
 *               &lt;enumeration value="N"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="CreationDate" type="{http://www.w3.org/2001/XMLSchema}date"/>
 *         &lt;element name="OFFENCE" type="{http://www.justice.gov.uk/2013/11/magistrates}LibraOffenceType" maxOccurs="unbounded"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "LibraCaseType", propOrder = {
    "libraCaseId",
    "cjsAreaCode",
    "maatCaseId",
    "asn",
    "documentLanguage",
    "inactive",
    "creationDate",
    "offence"
})
public class LibraCaseType {

    @XmlElement(name = "LibraCaseId", required = true)
    protected String libraCaseId;
    @XmlElement(name = "CJSAreaCode", required = true)
    protected String cjsAreaCode;
    @XmlElement(name = "MaatCaseId")
    protected String maatCaseId;
    @XmlElement(name = "ASN")
    protected String asn;
    protected String documentLanguage;
    @XmlElement(name = "Inactive", required = true)
    protected String inactive;
    @XmlElement(name = "CreationDate", required = true)
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar creationDate;
    @XmlElement(name = "OFFENCE", required = true)
    protected List<LibraOffenceType> offence;

    /**
     * Gets the value of the libraCaseId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLibraCaseId() {
        return libraCaseId;
    }

    /**
     * Sets the value of the libraCaseId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLibraCaseId(String value) {
        this.libraCaseId = value;
    }

    /**
     * Gets the value of the cjsAreaCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCJSAreaCode() {
        return cjsAreaCode;
    }

    /**
     * Sets the value of the cjsAreaCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCJSAreaCode(String value) {
        this.cjsAreaCode = value;
    }

    /**
     * Gets the value of the maatCaseId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMaatCaseId() {
        return maatCaseId;
    }

    /**
     * Sets the value of the maatCaseId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMaatCaseId(String value) {
        this.maatCaseId = value;
    }

    /**
     * Gets the value of the asn property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getASN() {
        return asn;
    }

    /**
     * Sets the value of the asn property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setASN(String value) {
        this.asn = value;
    }

    /**
     * Gets the value of the documentLanguage property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDocumentLanguage() {
        return documentLanguage;
    }

    /**
     * Sets the value of the documentLanguage property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDocumentLanguage(String value) {
        this.documentLanguage = value;
    }

    /**
     * Gets the value of the inactive property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getInactive() {
        return inactive;
    }

    /**
     * Sets the value of the inactive property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setInactive(String value) {
        this.inactive = value;
    }

    /**
     * Gets the value of the creationDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getCreationDate() {
        return creationDate;
    }

    /**
     * Sets the value of the creationDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setCreationDate(XMLGregorianCalendar value) {
        this.creationDate = value;
    }

    /**
     * Gets the value of the offence property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the offence property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getOFFENCE().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link LibraOffenceType }
     * 
     * 
     */
    public List<LibraOffenceType> getOFFENCE() {
        if (offence == null) {
            offence = new ArrayList<LibraOffenceType>();
        }
        return this.offence;
    }

}
