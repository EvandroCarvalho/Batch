package com.example.createFileBatch.reader;

import com.example.createFileBatch.feign.CallCustomerService;
import com.example.createFileBatch.model.Customer;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ReaderModel implements ItemReader<Customer> {

    @Autowired
    private CallCustomerService customerService;

    private List<Customer> lista;
    private Customer customer;
    private int contador;

    @Override
    public Customer read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
        lista = customerService.list();

        if(contador < lista.size()) {
            customer = lista.get(contador);
            contador++;
        } else {
            contador = 0;
            customer = null;
        }
        return customer;
    }
}
