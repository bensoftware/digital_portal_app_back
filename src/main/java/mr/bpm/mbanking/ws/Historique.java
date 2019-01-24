
package mr.bpm.mbanking.ws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Classe Java pour historique complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="historique">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="auth_cumu" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="date" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="genClearing" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="host" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="recharge" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="utilise_cumu" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "historique", propOrder = {
    "authCumu",
    "date",
    "genClearing",
    "host",
    "recharge",
    "utiliseCumu"
})
public class Historique {

    @XmlElement(name = "auth_cumu")
    protected double authCumu;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar date;
    protected String genClearing;
    protected String host;
    protected double recharge;
    @XmlElement(name = "utilise_cumu")
    protected double utiliseCumu;

    /**
     * Obtient la valeur de la propriété authCumu.
     * 
     */
    public double getAuthCumu() {
        return authCumu;
    }

    /**
     * Définit la valeur de la propriété authCumu.
     * 
     */
    public void setAuthCumu(double value) {
        this.authCumu = value;
    }

    /**
     * Obtient la valeur de la propriété date.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDate() {
        return date;
    }

    /**
     * Définit la valeur de la propriété date.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDate(XMLGregorianCalendar value) {
        this.date = value;
    }

    /**
     * Obtient la valeur de la propriété genClearing.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getGenClearing() {
        return genClearing;
    }

    /**
     * Définit la valeur de la propriété genClearing.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setGenClearing(String value) {
        this.genClearing = value;
    }

    /**
     * Obtient la valeur de la propriété host.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getHost() {
        return host;
    }

    /**
     * Définit la valeur de la propriété host.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setHost(String value) {
        this.host = value;
    }

    /**
     * Obtient la valeur de la propriété recharge.
     * 
     */
    public double getRecharge() {
        return recharge;
    }

    /**
     * Définit la valeur de la propriété recharge.
     * 
     */
    public void setRecharge(double value) {
        this.recharge = value;
    }

    /**
     * Obtient la valeur de la propriété utiliseCumu.
     * 
     */
    public double getUtiliseCumu() {
        return utiliseCumu;
    }

    /**
     * Définit la valeur de la propriété utiliseCumu.
     * 
     */
    public void setUtiliseCumu(double value) {
        this.utiliseCumu = value;
    }

}
