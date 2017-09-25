package gr.northdigital.essentials;

import com.sun.jndi.ldap.LdapName;

import javax.naming.InvalidNameException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;

public class Main {

  public static void main(String[] args) throws CertificateException, FileNotFoundException, InvalidNameException {
    CertificateFactory fact = CertificateFactory.getInstance("X.509");
    FileInputStream is = new FileInputStream("C:\\Users\\Panagiotis\\Documents\\ForBackup\\Projects\\northdigital\\ssl-demo\\demo.crt");
    X509Certificate cer = (X509Certificate) fact.generateCertificate(is);

    String name = cer.getSubjectDN().getName();

    LdapName dn = new LdapName(name);
    System.out.println(dn + " has " + dn.size() + " RDNs: ");
    for (int i = 0; i < dn.size(); i++) {
      System.out.println(dn.get(i));
    }
  }
}
