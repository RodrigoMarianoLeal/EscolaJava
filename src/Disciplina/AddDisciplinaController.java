package Disciplina;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.URL;

import java.util.LinkedList;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import view.util.Disciplina;


public class AddDisciplinaController implements Serializable, Initializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@FXML
	AnchorPane addDisciplina;
	@FXML
	TextField nomeNovaDisciplina;
	@FXML
	TextField codigoNovaDisciplina;
	@FXML
	private ChoiceBox<String> cargaHorariaNovoDisciplina;
	@FXML
	private ChoiceBox<String> creditosNovoDisciplina;
	@FXML
	private Button b1;

	@FXML
	public void adicionardisciplina(ActionEvent event) {
		this.addDisciplina.setVisible(true);

	}
	public void salvardisciplina(ActionEvent event) {
		
		if((codigoNovaDisciplina.getText()!=null)&& cargaHorariaNovoDisciplina.getValue()!=null&&creditosNovoDisciplina.getValue()!=null) {
			salvarNovoDisciplina();
		}else {
			System.out.println("error disciplina");
		}
	}


	public void salvarNovoDisciplina() {
		System.out.println("come�ou a salvar");
		String nome = nomeNovaDisciplina.getText();
		nome = nome.trim().replaceAll("\\s+", " ");
		
		String codigo = codigoNovaDisciplina.getText();
		codigo = codigo.trim().replaceAll("\\s+", " ");
		
		String creditos = creditosNovoDisciplina.getValue();
		String cargaHoraria = cargaHorariaNovoDisciplina.getValue();
		System.out.println("DEU LOAD");
		Disciplina novoDisciplina = new Disciplina(nome,codigo,cargaHoraria, creditos);
		System.out.println(novoDisciplina);
		adicionar(novoDisciplina);
	}
	public void limparTela() {
		nomeNovaDisciplina.setText("");
		codigoNovaDisciplina.setText("");
		creditosNovoDisciplina.setValue(null);
		cargaHorariaNovoDisciplina.setValue(null);
	}
	
	
	@SuppressWarnings("unchecked")
	public static LinkedList<Disciplina> Lista() {
		LinkedList<Disciplina> lista = null;
		try {
			FileInputStream fin = new FileInputStream("c:\\temp\\ListaDisciplina.ser");
			ObjectInputStream ois = new ObjectInputStream(fin);
			lista = ((LinkedList<Disciplina>) ois.readObject());
			ois.close();
			return lista;
		} catch (IOException e) {
			return lista;
		} catch (ClassNotFoundException e) {
			System.out.println("Error de leitura: " + e.getMessage());
		}
		return lista;
	}

	public void adicionar(Disciplina NovoDisciplina) {
		File tempFile = new File("c:\\temp\\ListaDisciplina.ser");
		boolean exists = tempFile.exists();
		if (!exists) {
			try {
				tempFile.createNewFile();
			} catch (IOException e) {
				System.out.println("An error occurred.");
			}

		}
		try {

			LinkedList<Disciplina> lista = Lista();
			if (lista == null) {
				lista = new LinkedList<Disciplina>();
			}
			lista.add(NovoDisciplina);
			System.out.println(NovoDisciplina);
			FileOutputStream fout = new FileOutputStream("c:\\temp\\ListaDisciplina.ser");
			ObjectOutputStream oos = new ObjectOutputStream(fout);
			oos.writeObject(lista);
			oos.close();
			limparTela();

		} catch (IOException e) {
			System.out.println("Error de escrita: " + e.getMessage());
		}

	}

	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		cargaHorariaNovoDisciplina.getItems().add("30h");
		cargaHorariaNovoDisciplina.getItems().add("60h");
		creditosNovoDisciplina.getItems().add("2");
		creditosNovoDisciplina.getItems().add("4");

	}

}
