
package mr.bpm.mbanking.ws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Classe Java pour monetiqueClass complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
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
     * Obtient la valeur de la propriété authorisationCumule.
     * 
     */
    public double getAuthorisationCumule() {
        return authorisationCumule;
    }

    /**
     * Définit la valeur de la propriété authorisationCumule.
     * 
     */
    public void setAuthorisationCumule(double value) {
        this.authorisationCumule = value;
    }

    /**
     * Obtient la valeur de la propriété clearing.
     * 
     */
    public double getClearing() {
        return clearing;
    }

    /**
     * Définit la valeur de la propriété clearing.
     * 
     */
    public void setClearing(double value) {
        this.clearing = value;
    }

    /**
     * Obtient la valeur de la propriété code.
     * 
     */
    public int getCode() {
        return code;
    }

    /**
     * Définit la valeur de la propriété code.
     * 
     */
    public void setCode(int value) {
        this.code = value;
    }

    /**
     * Obtient la valeur de la propriété codeAuth.
     * 
     */
    public double getCodeAuth() {
        return codeAuth;
    }

    /**
     * Définit la valeur de la propriété codeAuth.
     * 
     */
    public void setCodeAuth(double value) {
        this.codeAuth = value;
    }

    /**
     * Obtient la valeur de la propriété commission.
     * 
     */
    public double getCommission() {
        return commission;
    }

    /**
     * Définit la valeur de la propriété commission.
     * 
     */
    public void setCommission(double value) {
        this.commission = value;
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
     * Obtient la valeur de la propriété deviseCl.
     * 
     */
    public int getDeviseCl() {
        return deviseCl;
    }

    /**
     * Définit la valeur de la propriété deviseCl.
     * 
     */
    public void setDeviseCl(int value) {
        this.deviseCl = value;
    }

    /**
     * Obtient la valeur de la propriété deviseOrigine.
     * 
     */
    public int getDeviseOrigine() {
        return deviseOrigine;
    }

    /**
     * Définit la valeur de la propriété deviseOrigine.
     * 
     */
    public void setDeviseOrigine(int value) {
        this.deviseOrigine = value;
    }

    /**
     * Obtient la valeur de la propriété deviseSS.
     * 
     */
    public int getDeviseSS() {
        return deviseSS;
    }

    /**
     * Définit la valeur de la propriété deviseSS.
     * 
     */
    public void setDeviseSS(int value) {
        this.deviseSS = value;
    }

    /**
     * Obtient la valeur de la propriété etat.
     * 
     */
    public int getEtat() {
        return etat;
    }

    /**
     * Définit la valeur de la propriété etat.
     * 
     */
    public void setEtat(int value) {
        this.etat = value;
    }

    /**
     * Obtient la valeur de la propriété lieu.
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
     * Définit la valeur de la propriété lieu.
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
     * Obtient la valeur de la propriété montantCl.
     * 
     */
    public double getMontantCl() {
        return montantCl;
    }

    /**
     * Définit la valeur de la propriété montantCl.
     * 
     */
    public void setMontantCl(double value) {
        this.montantCl = value;
    }

    /**
     * Obtient la valeur de la propriété montantOrigine.
     * 
     */
    public double getMontantOrigine() {
        return montantOrigine;
    }

    /**
     * Définit la valeur de la propriété montantOrigine.
     * 
     */
    public void setMontantOrigine(double value) {
        this.montantOrigine = value;
    }

    /**
     * Obtient la valeur de la propriété montantTrans.
     * 
     */
    public double getMontantTrans() {
        return montantTrans;
    }

    /**
     * Définit la valeur de la propriété montantTrans.
     * 
     */
    public void setMontantTrans(double value) {
        this.montantTrans = value;
    }

    /**
     * Obtient la valeur de la propriété rechargeCl.
     * 
     */
    public double getRechargeCl() {
        return rechargeCl;
    }

    /**
     * Définit la valeur de la propriété rechargeCl.
     * 
     */
    public void setRechargeCl(double value) {
        this.rechargeCl = value;
    }

    /**
     * Obtient la valeur de la propriété rechargeCumule.
     * 
     */
    public double getRechargeCumule() {
        return rechargeCumule;
    }

    /**
     * Définit la valeur de la propriété rechargeCumule.
     * 
     */
    public void setRechargeCumule(double value) {
        this.rechargeCumule = value;
    }

    /**
     * Obtient la valeur de la propriété refCl.
     * 
     */
    public double getRefCl() {
        return refCl;
    }

    /**
     * Définit la valeur de la propriété refCl.
     * 
     */
    public void setRefCl(double value) {
        this.refCl = value;
    }

    /**
     * Obtient la valeur de la propriété refErone.
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
     * Définit la valeur de la propriété refErone.
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
     * Obtient la valeur de la propriété refTrans.
     * 
     */
    public double getRefTrans() {
        return refTrans;
    }

    /**
     * Définit la valeur de la propriété refTrans.
     * 
     */
    public void setRefTrans(double value) {
        this.refTrans = value;
    }

    /**
     * Obtient la valeur de la propriété solde.
     * 
     */
    public double getSolde() {
        return solde;
    }

    /**
     * Définit la valeur de la propriété solde.
     * 
     */
    public void setSolde(double value) {
        this.solde = value;
    }

    /**
     * Obtient la valeur de la propriété taux.
     * 
     */
    public double getTaux() {
        return taux;
    }

    /**
     * Définit la valeur de la propriété taux.
     * 
     */
    public void setTaux(double value) {
        this.taux = value;
    }

    /**
     * Obtient la valeur de la propriété type.
     * 
     */
    public int getType() {
        return type;
    }

    /**
     * Définit la valeur de la propriété type.
     * 
     */
    public void setType(int value) {
        this.type = value;
    }

    /**
     * Obtient la valeur de la propriété utiliseCumule.
     * 
     */
    public double getUtiliseCumule() {
        return utiliseCumule;
    }

    /**
     * Définit la valeur de la propriété utiliseCumule.
     * 
     */
    public void setUtiliseCumule(double value) {
        this.utiliseCumule = value;
    }

    /**
     * Obtient la valeur de la propriété verified.
     * 
     */
    public boolean isVerified() {
        return verified;
    }

    /**
     * Définit la valeur de la propriété verified.
     * 
     */
    public void setVerified(boolean value) {
        this.verified = value;
    }

}
