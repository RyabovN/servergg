package servlets;

import java.io.File;
import java.io.IOException;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

/**
 * Created by zak on 14.11.2015.
 */
public class AccountService {
    private static final String HTML_DIR = "src\\main\\sourses";

    public boolean addUser(UserProfile userProfile) {
        if(isBusyLogin(userProfile.getLogin()))
            return false;

        try {
		String filepath = HTML_DIR + "\\users.xml";
		DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
		Document doc = docBuilder.parse(filepath);

                Node users = doc.getFirstChild();
                
                Element profile = doc.createElement("profile");
                profile.setAttribute("login", userProfile.getLogin());
		users.appendChild(profile);
                
                Element password = doc.createElement("password");
		password.appendChild(doc.createTextNode(userProfile.getPassword()));
		profile.appendChild(password);

                Element email = doc.createElement("email");
                email.appendChild(doc.createTextNode(userProfile.getEmail()));
                profile.appendChild(email);

		TransformerFactory transformerFactory = TransformerFactory.newInstance();
		Transformer transformer = transformerFactory.newTransformer();
		DOMSource source = new DOMSource(doc);
		StreamResult result = new StreamResult(new File(filepath));
		transformer.transform(source, result);

	   } catch (ParserConfigurationException pce) {
		pce.printStackTrace();
	   } catch (TransformerException tfe) {
		tfe.printStackTrace();
	   } catch (IOException ioe) {
		ioe.printStackTrace();
	   } catch (SAXException sae) {
		sae.printStackTrace();
	   }
        return true;
    }
    
    public UserProfile getUser(String userName) {
        try {
            File xmlFile = new File(HTML_DIR + "\\users.xml");
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(xmlFile);
            
            doc.getDocumentElement().normalize();
            
            NodeList nList = doc.getElementsByTagName("profile");
            
            for (int i = 0; i < nList.getLength(); i++) {
                Node nNode = nList.item(i);
                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) nNode;
                     
                    if(eElement.getAttribute("login").equals(userName)) {
                        return new UserProfile( 
                            eElement.getAttribute("login"), 
                            eElement.getElementsByTagName("password").item(0).getTextContent(), 
                            eElement.getElementsByTagName("email").item(0).getTextContent()
                        );
                    }
                }
            }
        } catch (Exception e) {
            return null;
        }
        return null;
    }
    
    public boolean isBusyLogin(String userName) {
        if(getUser(userName) == null)
            return false;
        return true;
    }

    public void addSessionUser(javax.servlet.http.HttpServletRequest request, UserProfile userProfile) {
        javax.servlet.http.HttpSession session = request.getSession();
        String username = userProfile.getLogin();
        session.setAttribute("UserName", username);
    }

    public UserProfile getSessionUser(javax.servlet.http.HttpServletRequest request) {
        javax.servlet.http.HttpSession session = request.getSession();
        return getUser((String)session.getAttribute("UserName"));
    }
    
    public void removeSessionUser(javax.servlet.http.HttpServletRequest request, UserProfile userProfile) {
        javax.servlet.http.HttpSession session = request.getSession();
        String username = userProfile.getLogin();
        session.setAttribute("UserName", username);
        session.removeAttribute("UserName");
    }
}