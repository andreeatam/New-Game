package Test;

import Model.Monom;
import Model.Polinom;
import org.junit.Test;

import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class MonomTest {

    @Test
    public void validMonomPower()
    {
        try {
            Monom monom = new Monom(3, 15);
            assert (true);
        } catch(Exception e){
            fail("Did not expect an exception");
        }
    }

    @Test
    public void invalidMonomPower()
    {
        try {
            Monom monom = new Monom(3, -10);
            assert (false);
        } catch(Exception e){
            assert (true);
        }
    }

    @Test
    public void invalidMonomPower2()
    {
        try {
            Monom monom = new Monom(3, 100);
            assert (false);
        } catch(Exception e){
            assert (true);
        }
    }


    @Test
    public void invalid_BVA()
    {
        try{
            Monom monom = new Monom(6,Integer.MIN_VALUE);
            assert(false);
        } catch(Exception e) {
            assert (true);
        }
    }

    @Test
    public void invalid_BVA1()
    {
        try{
            Monom monom = new Monom(6,Integer.MIN_VALUE+1);
            assert(false);
        } catch(Exception e) {
            assert (true);
        }
    }

    @Test
    public void invalid_BVA2()
    {
        try{
            Monom monom = new Monom(6,-1);
            assert(false);
        } catch(Exception e) {
            assert (true);
        }
    }

    @Test
    public void valid_BVA3()
    {
        try{
            Monom monom = new Monom(6,0);
            assert(true);
        } catch(Exception e) {
            fail("Did not expect an exception");
        }
    }

    @Test
    public void valid_BVA4()
    {
        try{
            Monom monom = new Monom(6,1);
            assert(true);
        } catch(Exception e) {
            fail("Did not expect an exception");
        }
    }

    @Test
    public void valid_BVA5()
    {
        try{
            Monom monom = new Monom(6,49);
            assert(true);
        } catch(Exception e) {
            fail("Did not expect an exception");
        }
    }

    @Test
    public void valid_BVA6()
    {
        try{
            Monom monom = new Monom(6,50);
            assert(true);
        } catch(Exception e) {
            fail("Did not expect an exception");
        }
    }

    @Test
    public void invalid_BVA7()
    {
        try{
            Monom monom = new Monom(6,51);
            assert(false);
        } catch(Exception e) {
            assert (true);
        }
    }

    @Test
    public void invalid_BVA8()
    {
        try{
            Monom monom = new Monom(6,Integer.MAX_VALUE-1);
            assert(false);
        } catch(Exception e) {
            assert (true);
        }
    }

    @Test
    public void invalid_BVA9()
    {
        try{
            Monom monom = new Monom(6,Integer.MAX_VALUE);
            assert(false);
        } catch(Exception e) {
            assert (true);
        }
    }
}