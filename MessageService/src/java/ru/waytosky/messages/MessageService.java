
package ru.waytosky.messages;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

/**
 *
 * @author Ayrat
 */

@WebService(serviceName = "MessS")
public class MessageService {

    @EJB
    MessagesFacade messageDao;
    /**
     * Web service operation
     */
    @WebMethod(operationName = "addMessage")
    public boolean addMessage(@WebParam(name = "message") String message) {
        //TODO write your implementation code here:
        System.out.println(messageDao.count());
        return false;
    }
    
}
