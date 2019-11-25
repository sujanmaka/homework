/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.homework.mdb;

import com.homework.converter.DtoToEntityConverter;
import com.homework.dto.HeadMasterDto;
import com.homework.ejb.SchoolEjb;
import javax.ejb.ActivationConfigProperty;
import javax.ejb.EJB;
import javax.ejb.MessageDriven;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

/**
 *
 * @author sujan
 */
@MessageDriven(activationConfig = {
    @ActivationConfigProperty(propertyName = "destinationLookup", propertyValue = "jms/dest")
    ,
        @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue")
})
public class MBean implements MessageListener {

    @EJB
    SchoolEjb schoolEjb;

    public MBean() {
    }

    @Override
    public void onMessage(Message message) {
        TextMessage tmsg = null;
        tmsg = (TextMessage) message;
        try {
            System.out.println(tmsg.getText());
            String[] str = tmsg.getText().split(",");
            HeadMasterDto headMasterDto = new HeadMasterDto();
            headMasterDto.setName(str[0]);
            headMasterDto.setAge(str[1]);
            headMasterDto.setEducation(str[2]);
            schoolEjb.saveHeadMaster(DtoToEntityConverter.convertHeadMasterDtoToHeadMaster(headMasterDto));
        } catch (JMSException e) {
            e.printStackTrace();
        }

    }

}
