package fr.epita.bank.datamodel;

public class StockOrder {
	int quantity;
	double unitPrice;
	double commission;

	Stock stock;
	InvestmentAccount account;
}
