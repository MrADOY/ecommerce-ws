package fr.commerce.ecommerce.rest;


import fr.commerce.ecommerce.rest.model.Product;
import org.hibernate.Transaction;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import javax.naming.NamingException;
import javax.persistence.EntityManager;
import javax.transaction.UserTransaction;
import javax.ws.rs.core.Response;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.any;

@RunWith(MockitoJUnitRunner.class)
public class ProductTest {

    @Test
    public void testProductNotAvailable() throws Exception {

        EntityManager entityManager = mock(EntityManager.class, Mockito.RETURNS_DEEP_STUBS);
        UserTransaction transaction = mock(UserTransaction.class, Mockito.RETURNS_DEEP_STUBS);

        Product product = Product
                .builder().id(1).name("product 1").description("description").available(true).build();

        when(entityManager.createQuery(anyString(), any()).setParameter(anyString(), any()).getSingleResult())
                .thenReturn(product);

        ProductsController obj = new ProductsController();
        obj.setEm(entityManager);
        obj.setTransaction(transaction);

        //2. Test method
        Response result = obj.productNotAvailable(1);

        Product productResult = (Product)result.getEntity();

        //3. Verify result
        assertEquals(productResult.isAvailable(), false);
    }

    @Test
    public void testFindProductsWithId(){

        EntityManager entityManager = mock(EntityManager.class, Mockito.RETURNS_DEEP_STUBS);

        Product product = Product.builder().id(1).name("product 1").description("description").build();

        when(entityManager.createQuery(anyString(), any()).setParameter(anyString(), any()).getSingleResult())
                .thenReturn(product);

        ProductsController obj = new ProductsController();
        obj.setEm(entityManager);

        //2. Test method
        Response result = obj.getProductsById(1);

        Product productResult = (Product)result.getEntity();

        //3. Verify result
        assertEquals(productResult.getId(), 1);
    }

    @Test
    public void testFindProducts() throws SQLException, NamingException {

        EntityManager entityManager = mock(EntityManager.class, Mockito.RETURNS_DEEP_STUBS);

        List<Product> products = Arrays.asList(Product.builder().id(1).name("product 1").description("description").build(),
                Product.builder().id(2).name("product 2").description("description").build());

        when(entityManager.createQuery(anyString(), any()).getResultList()).thenReturn(Collections.singletonList(products));

        ProductsController obj = new ProductsController();
        obj.setEm(entityManager);

        //2. Test method
        Response result = obj.getAllProducts();
        List<Product> productsList = (List<Product>)((List)result.getEntity()).get(0);

        //3. Verify result
        assertEquals(productsList.size(), 2);
    }
}
