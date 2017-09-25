package gr.northdigital.essentials;

import org.bouncycastle.asn1.ASN1InputStream;
import org.bouncycastle.asn1.ASN1OctetString;

import javax.naming.InvalidNameException;
import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;

public class Main {
  public static String getExtensionValue(X509Certificate certificate, String oid) throws IOException {
    byte[] bytes = certificate.getExtensionValue(oid);
    if (bytes == null) {
      return null;
    }
    ASN1InputStream aIn = new ASN1InputStream(new ByteArrayInputStream(bytes));
    ASN1OctetString octs = (ASN1OctetString) aIn.readObject();
    aIn = new ASN1InputStream(new ByteArrayInputStream(octs.getOctets()));
    return aIn.readObject().toString();
  }

  public static void main(String[] args) throws CertificateException, IOException, InvalidNameException {
    CertificateFactory fact = CertificateFactory.getInstance("X.509");
    FileInputStream is = new FileInputStream("C:\\Users\\Panagiotis\\Documents\\ForBackup\\Projects\\northdigital\\ssl-demo\\demo.crt");
    X509Certificate cer = (X509Certificate) fact.generateCertificate(is);

//    String name = cer.getSubjectDN().getName();
//
//    LdapName dn = new LdapName(name);
//    System.out.println(dn + " has " + dn.size() + " RDNs: ");
//    for (int i = 0; i < dn.size(); i++) {
//      System.out.println(dn.get(i));
//    }

    System.out.println(getExtensionValue(cer, "1.1.1.3.7"));
  }
}
