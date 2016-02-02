package com.algaworks;

import java.util.ArrayList;
import java.util.List;

import com.algaworks.desconto.CalculadoraFaixaDesconto;

public class Pedido {

	
	private List<ItemPedido> items = new ArrayList<>();
	
	private CalculadoraFaixaDesconto calculadoraFaixaDesconto;
	
	public Pedido(CalculadoraFaixaDesconto calculadoraFaixaDesconto) {
		this.calculadoraFaixaDesconto = calculadoraFaixaDesconto;
	}

	public void adicionarItem(ItemPedido itemPedido) {
		validarQuantidadeItens(itemPedido);
		items.add(itemPedido);
	}

	private void validarQuantidadeItens(ItemPedido itemPedido) {
		if(itemPedido.getQuantidade() < 0)
			throw new QuantidadeItenInvalidaException();
	}
	
	public ResumoPedido resumo(){
		
		double valorTotal = items.stream().mapToDouble(i -> i.getValorUnitario() * i.getQuantidade()).sum();
		double desconto = calculadoraFaixaDesconto.desconto(valorTotal);
		
		return new ResumoPedido(valorTotal, desconto);
	}

}
