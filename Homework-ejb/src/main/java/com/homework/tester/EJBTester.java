/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.homework.tester;

import com.homework.ejb.SchoolEjb;
import com.homework.entity.HeadMaster;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.util.List;
import java.util.Properties;
import javax.jms.Queue;
import javax.jms.ObjectMessage;
import javax.jms.QueueConnection;
import javax.jms.QueueConnectionFactory;
import javax.jms.QueueSender;
import javax.jms.QueueSession;
import javax.naming.InitialContext;
import javax.naming.NamingException;


public class EJBTester {

    BufferedReader brConsoleReader = null;
    Properties props;
    InitialContext ctx;

    {
        props = new Properties();
        try {
            props.load(new FileInputStream("jndi.properties"));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        try {
            ctx = new InitialContext(props);
        } catch (NamingException ex) {
            ex.printStackTrace();
        }
        brConsoleReader
                = new BufferedReader(new InputStreamReader(System.in));
    }

    public static void main(String[] args) {

        EJBTester ejbTester = new EJBTester();

        ejbTester.testMessageBeanEjb();
    }

    private void showGUI() {
        System.out.println("**********************");
        System.out.println("Welcome to Headmaster Screen");
        System.out.println("**********************");
        System.out.print("Options \n1. Add Headmaster\n2. Exit \nEnter Choice: ");
    }

    private void testMessageBeanEjb() {

        try {
            int choice = 1;
            Queue queue = (Queue) ctx.lookup("/queue/HeadMasterQueue");
            QueueConnectionFactory factory
                    = (QueueConnectionFactory) ctx.lookup("ConnectionFactory");
            QueueConnection connection = factory.createQueueConnection();
            QueueSession session
                    = connection.createQueueSession(false, QueueSession.AUTO_ACKNOWLEDGE);
            QueueSender sender = session.createSender(queue);

            while (choice != 2) {
                String headMasterName;
                showGUI();
                String strChoice = brConsoleReader.readLine();
                choice = Integer.parseInt(strChoice);
                if (choice == 1) {
                    System.out.print("Enter Head Master name: ");
                    headMasterName = brConsoleReader.readLine();
                    HeadMaster headMaster = new HeadMaster();
                    headMaster.setName(headMasterName);
                    ObjectMessage objectMessage
                            = session.createObjectMessage((Serializable) headMaster);
                    sender.send(objectMessage);
                } else if (choice == 2) {
                    break;
                }
            }

            SchoolEjb schoolEjb
                    = (SchoolEjb) ctx.lookup("SchoolEjb/remote");

            List<HeadMaster> headMasters = schoolEjb.getAllHeadMasters();

            int i = 0;
            for (HeadMaster headMaster : headMasters) {
                System.out.println((i + 1) + ". " + headMaster.getName());
                i++;
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        } finally {
            try {
                if (brConsoleReader != null) {
                    brConsoleReader.close();
                }
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }
}
