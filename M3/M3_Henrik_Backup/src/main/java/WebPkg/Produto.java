package WebPkg;

public class Produto {
	String descricao;
	double preco;
	public Produto(String descricao, double preco) {
		this.descricao = descricao;
		this.preco = preco;
	}

	public String getPreco() {
		// TODO Auto-generated method stub
		return String.valueOf(preco);
	}

	public String getDescricao() {
		// TODO Auto-generated method stub
		return descricao;
	}

}
