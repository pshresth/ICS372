import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * The collection class for Client objects
 * 
 * @author Matt Carlson, Jamison Czech, Slava Makharovich, Prashant Shrestha
 */

public class ClientList implements Serializable {

	private static ClientList clientList;
	private List clients = new LinkedList();

	/*
	 * Private constructor to create singleton
	 */
	private ClientList() {
	}

	/**
	 * ClientList singleton
	 * @return the ClientList singleton object
	 */
	public static ClientList instance() {
		return clientList == null ? (clientList = new ClientList()) : clientList;
	}
	

	/**
	 * Adds a Client to the collection
	 * @param client
	 * @return
	 * 	 A boolean value indicating successful addition to collection.
	 */
	public boolean insertClient(Client client) {
		clients.add(client);
		return true;
	}
	
	/**
	 * searches for a client in the collection
	 * @param clientID
	 * @return a Client if found or null if not found
	 */
	public Client search(String clientID) {
	    for (Iterator iterator = clients.iterator(); iterator.hasNext();) {
	      Client client = (Client) iterator.next();
	      if (client.getClientID().equals(clientID)) {
	        return client;
	      }
	    }
	    return null;
    }

    /**
     * return a list of clients
     */
    public void getClientList(){
        Iterator result = clients.iterator();
        System.out.println("The Clients are: ");
        while(result.hasNext()) {
            System.out.println(result.next());
        }
    }
	 
	/**
	 * removes a client with the given clientID from the collection
	 * @param clientID
	 * @return true if Client exists in the collection, or false otherwise
	 */
	public boolean removeClient(String clientID) {
		Client client = search(clientID);
		if (client == null) {
			return false;
		}
		else {
			return clients.remove(clientID);
		}
	}
	
	/**
	 * write objects for serialization
	 * @param output stream
	 */
	private void writeObject(ObjectOutputStream output) {
	    try {
	      //output.defaultWriteObject();
	      output.writeObject(clientList);
	    } 
	    catch(IOException ioe) {
	      System.out.println(ioe);
	    }
	  }
	
	  /**
	   * read serialized object
	   * @param input stream
	   */
	private void readObject(ObjectInputStream input) {
		try {
			if (clientList != null) {
				return;
			} 
			else {
				    input.defaultReadObject();
				if (clientList == null) {
					clientList = (ClientList) input.readObject();
				} 
				else {
					input.readObject();
				}
			}
		} 
		catch(IOException ioe) {
			System.out.println("in ClientList readObject \n" + ioe);
		} 
		catch(ClassNotFoundException cnfe) {
				cnfe.printStackTrace();
		}
	}
	
	/**
	 * String of the client
	 */
	@Override
	public String toString() {
		return clients.toString();
	}

}
