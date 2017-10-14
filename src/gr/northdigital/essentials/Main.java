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
    ASN1InputStream asn1InputStream = new ASN1InputStream(new ByteArrayInputStream(bytes));
    ASN1OctetString asn1OctetString = (ASN1OctetString) asn1InputStream.readObject();
    asn1InputStream = new ASN1InputStream(new ByteArrayInputStream(asn1OctetString.getOctets()));
    return asn1InputStream.readObject().toString();
  }

  public static void main(String[] args) throws CertificateException, IOException, InvalidNameException {
    CertificateFactory certificateFactory = CertificateFactory.getInstance("X.509");
    FileInputStream fileInputStream = new FileInputStream("C:\\Users\\Panagiotis\\Documents\\ForBackup\\Projects\\northdigital\\ssl-demo\\user1.crt");
    X509Certificate cer = (X509Certificate) certificateFactory.generateCertificate(fileInputStream);

    System.out.println(getExtensionValue(cer, "1.2.3.101"));
    System.out.println(getExtensionValue(cer, "1.2.3.102"));
  }
}
