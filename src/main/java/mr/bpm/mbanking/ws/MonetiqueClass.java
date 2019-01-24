
package mr.bpm.mbanking.ws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Classe Java pour monetiqueClass complex type.
 * 
 * <p>Le fragment de sch�ma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="monetiqueClass">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="authorisationCumule" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="clearing" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="code" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="codeAuth" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="commission" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="date" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="deviseCl" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="deviseOrigine" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="deviseSS" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="etat" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="lieu" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="montantCl" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="montantOrigine" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="montantTrans" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="rechargeCl" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="rechargeCumule" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="refCl" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="refErone" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="refTrans" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="solde" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="taux" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="type" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="utiliseCumule" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="verified" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "monetiqueClass", propOrder = {
    "authorisationCumule",
    "clearing",
    "code",
    "codeAuth",
    "commission",
    "date",
    "deviseCl",
    "deviseOrigine",
    "deviseSS",
    "etat",
    "lieu",
    "montantCl",
    "montantOrigine",
    "montantTrans",
    "rechargeCl",
    "rechargeCumule",
    "refCl",
    "refErone",
    "refTrans",
    "solde",
    "taux",
    "type",
    "utiliseCumule",
    "verified"
})
public class MonetiqueClass {

    protected double authorisationCumule;
    protected double clearing;
    protected int code;
    protected double codeAuth;
    protected double commission;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar date;
    protected int deviseCl;
    protected int deviseOrigine;
    protected int deviseSS;
    protected int etat;
    protected String lieu;
    protected double montantCl;
    protected double montantOrigine;
    protected double montantTrans;
    protected double rechargeCl;
    protected double rechargeCumule;
    protected double refCl;
    protected String refErone;
    protected double refTrans;
    protected double solde;
    protected double taux;
    protected int type;
    protected double utiliseCumule;
    protected boolean verified;

    /**
     * Obtient la valeur de la propri�t� authorisationCumule.
     * 
     */
    public double getAuthorisationCumule() {
        return authorisationCumule;
    }

    /**
     * D�finit la valeur de la propri�t� authorisationCumule.
     * 
     */
    public void setAuthorisationCumule(double value) {
        this.authorisationCumule = value;
    }

    /**
     * Obtient la valeur de la propri�t� clearing.
     * 
     */
    public double getClearing() {
        return clearing;
    }

    /**
     * D�finit la valeur de la propri�t� clearing.
     * 
     */
    public void setClearing(double value) {
        this.clearing = value;
    }

    /**
     * Obtient la valeur de la propri�t� code.
     * 
     */
    public int getCode() {
        return code;
    }

    /**
     * D�finit la valeur de la propri�t� code.
     * 
     */
    public void setCode(int value) {
        this.code = value;
    }

    /**
     * Obtient la valeur de la propri�t� codeAuth.
     * 
     */
    public double getCodeAuth() {
        return codeAuth;
    }

    /**
     * D�finit la valeur de la propri�t� codeAuth.
     * 
     */
    public void setCodeAuth(double value) {
        this.codeAuth = value;
    }

    /**
     * Obtient la valeur de la propri�t� commission.
     * 
     */
    public double getCommission() {
        return commission;
    }

    /**
     * D�finit la valeur de la propri�t� commission.
     * 
     */
    public void setCommission(double value) {
        this.commission = value;
    }

    /**
     * Obtient la valeur de la propri�t� date.
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
     * D�finit la valeur de la propri�t� date.
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
     * Obtient la valeur de la propri�t� deviseCl.
     * 
     */
    public int getDeviseCl() {
        return deviseCl;
    }

    /**
     * D�finit la valeur de la propri�t� deviseCl.
     * 
     */
    public void setDeviseCl(int value) {
        this.deviseCl = value;
    }

    /**
     * Obtient la valeur de la propri�t� deviseOrigine.
     * 
     */
    public int getDeviseOrigine() {
        return deviseOrigine;
    }

    /**
     * D�finit la valeur de la propri�t� deviseOrigine.
     * 
     */
    public void setDeviseOrigine(int value) {
        this.deviseOrigine = value;
    }

    /**
     * Obtient la valeur de la propri�t� deviseSS.
     * 
     */
    public int getDeviseSS() {
        return deviseSS;
    }

    /**
     * D�finit la valeur de la propri�t� deviseSS.
     * 
     */
    public void setDeviseSS(int value) {
        this.deviseSS = value;
    }

    /**
     * Obtient la valeur de la propri�t� etat.
     * 
     */
    public int getEtat() {
        return etat;
    }

    /**
     * D�finit la valeur de la propri�t� etat.
     * 
     */
    public void setEtat(int value) {
        this.etat = value;
    }

    /**
     * Obtient la valeur de la propri�t� lieu.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLieu() {
        return lieu;
    }

    /**
     * D�finit la valeur de la propri�t� lieu.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLieu(String value) {
        this.lieu = value;
    }

    /**
     * Obtient la valeur de la propri�t� montantCl.
     * 
     */
    public double getMontantCl() {
        return montantCl;
    }

    /**
     * D�finit la valeur de la propri�t� montantCl.
     * 
     */
    public void setMontantCl(double value) {
        this.montantCl = value;
    }

    /**
     * Obtient la valeur de la propri�t� montantOrigine.
     * 
     */
    public double getMontantOrigine() {
        return montantOrigine;
    }

    /**
     * D�finit la valeur de la propri�t� montantOrigine.
     * 
     */
    public void setMontantOrigine(double value) {
        this.montantOrigine = value;
    }

    /**
     * Obtient la valeur de la propri�t� montantTrans.
     * 
     */
    public double getMontantTrans() {
        return montantTrans;
    }

    /**
     * D�finit la valeur de la propri�t� montantTrans.
     * 
     */
    public void setMontantTrans(double value) {
        this.montantTrans = value;
    }

    /**
     * Obtient la valeur de la propri�t� rechargeCl.
     * 
     */
    public double getRechargeCl() {
        return rechargeCl;
    }

    /**
     * D�finit la valeur de la propri�t� rechargeCl.
     * 
     */
    public void setRechargeCl(double value) {
        this.rechargeCl = value;
    }

    /**
     * Obtient la valeur de la propri�t� rechargeCumule.
     * 
     */
    public double getRechargeCumule() {
        return rechargeCumule;
    }

    /**
     * D�finit la valeur de la propri�t� rechargeCumule.
     * 
     */
    public void setRechargeCumule(double value) {
        this.rechargeCumule = value;
    }

    /**
     * Obtient la valeur de la propri�t� refCl.
     * 
     */
    public double getRefCl() {
        return refCl;
    }

    /**
     * D�finit la valeur de la propri�t� refCl.
     * 
     */
    public void setRefCl(double value) {
        this.refCl = value;
    }

    /**
     * Obtient la valeur de la propri�t� refErone.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRefErone() {
        return refErone;
    }

    /**
     * D�finit la valeur de la propri�t� refErone.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRefErone(String value) {
        this.refErone = value;
    }

    /**
     * Obtient la valeur de la propri�t� refTrans.
     * 
     */
    public double getRefTrans() {
        return refTrans;
    }

    /**
     * D�finit la valeur de la propri�t� refTrans.
     * 
     */
    public void setRefTrans(double value) {
        this.refTrans = value;
    }

    /**
     * Obtient la valeur de la propri�t� solde.
     * 
     */
    public double getSolde() {
        return solde;
    }

    /**
     * D�finit la valeur de la propri�t� solde.
     * 
     */
    public void setSolde(double value) {
        this.solde = value;
    }

    /**
     * Obtient la valeur de la propri�t� taux.
     * 
     */
    public double getTaux() {
        return taux;
    }

    /**
     * D�finit la valeur de la propri�t� taux.
     * 
     */
    public void setTaux(double value) {
        this.taux = value;
    }

    /**
     * Obtient la valeur de la propri�t� type.
     * 
     */
    public int getType() {
        return type;
    }

    /**
     * D�finit la valeur de la propri�t� type.
     * 
     */
    public void setType(int value) {
        this.type = value;
    }

    /**
     * Obtient la valeur de la propri�t� utiliseCumule.
     * 
     */
    public double getUtiliseCumule() {
        return utiliseCumule;
    }

    /**
     * D�finit la valeur de la propri�t� utiliseCumule.
     * 
     */
    public void setUtiliseCumule(double value) {
        this.utiliseCumule = value;
    }

    /**
     * Obtient la valeur de la propri�t� verified.
     * 
     */
    public boolean isVerified() {
        return verified;
    }

    /**
     * D�finit la valeur de la propri�t� verified.
     * 
     */
    public void setVerified(boolean value) {
        this.verified = value;
    }

}
