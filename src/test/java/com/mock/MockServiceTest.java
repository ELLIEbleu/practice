package com.mock;

import com.alibaba.fastjson.JSONObject;
import org.junit.Test;
import org.mockito.MockingDetails;
import org.mockito.Mockito;
import org.mockito.mock.MockCreationSettings;

import java.util.Hashtable;
import java.util.LinkedList;
import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;
import static org.springframework.data.mongodb.core.mapping.ShardKey.hash;

public class MockServiceTest {

    @Test
    public void testMock() {
        List mockedList = mock(List.class);

        mockedList.add("one");
        mockedList.clear();
        System.out.println(mockedList.size());

        verify(mockedList).add("one");
//        verify(mockedList).clear();
        System.out.println(mockedList.size());

        LinkedList list = mock(LinkedList.class);
        when(list.get(0)).thenReturn("first");
        when(list.get(100)).thenReturn("second");

        System.out.println(list.get(0));
        System.out.println(list.get(100));
        System.out.println(list.get(190));

        List mock = mock(List.class);
        when(mock.size()).thenReturn(80);
        System.out.println(mock.size());
        reset(mock);
        System.out.println(mock.size());
    }

    @Test
    public void testSpy() {
        List list = new LinkedList();
        List spy = spy(list);

        when(spy.size()).thenReturn(100);
        spy.add("one");
        spy.add("two");
        spy.add("three");

        System.out.println(spy.get(0));
        System.out.println(spy.size());

        verify(spy).add("one");
        verify(spy).add("two");
        System.out.println(spy.size());

        when(spy.get(2)).thenReturn("foo");
        System.out.println(spy.get(2));

        System.out.println(doReturn("new foo").when(spy).get(0));

        Foo mock = mock(Foo.class);
        when(mock.someMethod()).thenCallRealMethod();
        when(mock.say()).thenReturn("testSay");

        System.out.println(mock.someMethod());
        System.out.println(mock.say());
    }

    @Test
    public void test2() {
        Seller seller = mock(Seller.class);
        Shop shop = new Shop(seller);
        given(seller.askForBread()).willReturn(new Bread());

        Goods goods = shop.buyBread();
    }

    @Test
    public void testMockDetails(){
        Bread someObject = new Bread();
        System.out.println(mockingDetails(someObject).isMock());
        System.out.println(mockingDetails(someObject).isSpy());

        Bread mockBread = mock(Bread.class);

        MockingDetails details = mockingDetails(mockBread);
        MockCreationSettings<?> mockCreationSettings = details.getMockCreationSettings();
        System.out.println(mockCreationSettings.getTypeToMock());
        System.out.println(mockCreationSettings.getDefaultAnswer());

        System.out.println(details.getInvocations());
        System.out.println(details.getStubbings());
        System.out.println(details.printInvocations());

        System.out.println("********************");
        Foo mock = Mockito.mock(Foo.class,withSettings().lenient());
        System.out.println(mock.someMethod());

    }

    @Test
    public void testHash(){
        System.out.println(JSONObject.toJSONString(hash("mysql")));
        System.out.println(JSONObject.toJSONString(hash("mongodb")));
        System.out.println(JSONObject.toJSONString(hash("SQL Server")));

        Hashtable<Integer,String> hm = new Hashtable<>();
        hm.put(1,"APP");
        hm.put(2,"Div");
        hm.put(3,"Best");
        hm.put(4,"JAVA");
        hm.put(2,"Def");

        System.out.println(hm);

    }


}
