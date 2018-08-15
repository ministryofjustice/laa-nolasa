
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
 *         &lt;element name="UNLink" type="{http://www.justice.gov.uk/2013/11/magistrates}UNLinkRequestType"/>
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
    "unLink"
})
@XmlRootElement(name = "UNLinkRequest")
public class UNLinkRequest {

    @XmlElement(name = "UNLink", required = true)
    protected UNLinkRequestType unLink;

    /**
     * Gets the value of the unLink property.
     * 
     * @return
     *     possible object is
     *     {@link UNLinkRequestType }
     *     
     */
    public UNLinkRequestType getUNLink() {
        return unLink;
    }

    /**
     * Sets the value of the unLink property.
     * 
     * @param value
     *     allowed object is
     *     {@link UNLinkRequestType }
     *     
     */
    public void setUNLink(UNLinkRequestType value) {
        this.unLink = value;
    }

}
