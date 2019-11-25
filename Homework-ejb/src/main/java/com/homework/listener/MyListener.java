/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.homework.listener;

import com.homework.ejb.SchoolEjb;
import com.homework.entity.HeadMaster;
import javax.annotation.Resource;
import javax.ejb.ActivationConfigProperty;
import javax.ejb.EJB;
import javax.ejb.MessageDriven;
import javax.ejb.MessageDrivenContext;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;

//@MessageDriven(
//   name = "HeadMasterMessageHandler",
//   activationConfig = {
//      @ActivationConfigProperty( propertyName = "destinationType", 
//                                 propertyValue = "javax.jms.Queue"),
//      @ActivationConfigProperty( propertyName = "destination", 
//                                 propertyValue ="/queue/HeadMasterQueue")
//   }
//)
public class MyListener implements MessageListener {
 
   @Resource
   private MessageDrivenContext mdctx;  
 
   @EJB
   SchoolEjb schoolEjb;
 
   public MyListener() {        
   }
 
   public void onMessage(Message message) {
       ObjectMessage objectMessage = null;
      try {
         objectMessage = (ObjectMessage) message;
          HeadMaster headMaster = (HeadMaster) objectMessage.getObject(); 
          schoolEjb.saveHeadMaster(headMaster);
 
      } catch (JMSException ex) {
         mdctx.setRollbackOnly();
      }    
   }
}
