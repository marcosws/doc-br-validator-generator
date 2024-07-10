package com.github.marcosws.docbr;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;


@TestMethodOrder(OrderAnnotation.class)
public class DocBrTest {
	
	@Test
	@Order(1)
	public void testIsCpf() {
		Assertions.assertTrue(DocBr.isCpf("150.516.877-50"));
	}
	@Test
	@Order(2)
	public void testIsCnpj() {
		Assertions.assertTrue(DocBr.isCnpj("17.658.547/0001-81"));
	}
	@Test
	@Order(3)
	public void testGenerateCpfValid() {
		Assertions.assertTrue(DocBr.isCpf(DocBr.generateCpf()));
	}
	@Test
	@Order(4)
	public void testGenerateCnpjValid() {
		Assertions.assertTrue(DocBr.isCnpj(DocBr.generateCnpj()));
	}
	@Test
	@Order(5)
	public void testMaskCpfCnpjWithCpf() {
		Assertions.assertEquals("150.516.877-50", DocBr.maskCpfCnpj("15051687750"));
	}
	@Test
	@Order(6)
	public void testMaskCpfCnpjWithCnpj() {
		Assertions.assertEquals("17.658.547/0001-81", DocBr.maskCpfCnpj("17658547000181"));
	}


	
	

}
