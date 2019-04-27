package view;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import javax.swing.JOptionPane;

import controller.ClienteFactoryMethod;
import controller.UsuarioFacade;
import dao.AdultoDAO;
import dao.CriancaDAO;
import dao.UsuarioDAO;
import model.Adulto;
import model.Crianca;
import model.Usuario;

public class Main {
	public static void main(String[] args) throws SQLException {
		Scanner sc = new Scanner(System.in);
		boolean op=true; //variavel de loop geral
		do {
			//menu principal
			System.out.println("\n\t--- MENU ---\n");
			System.out.println("1 - Login");
			System.out.println("0 - Sair");
			int opcao = sc.nextInt();
			switch(opcao) {
			case 1:
				//login de usuario
				System.out.println("\n\t--- LOGIN ---\n");
				System.out.println("Entre com seu nome e senha!");
				System.out.print("Nome : ");
				String nomeLog = sc.next();
				System.out.print("Senha : ");
				String senhaLog = sc.next();
				//varialvel boleana que recebe o metodo 
				//do getInstance e o metodo facadeLogin
				//retornando true or false 
				boolean login = UsuarioFacade.getINSTANCE().facadeLogin(nomeLog, senhaLog);
				System.out.println();
				if(login!=false) {
					JOptionPane.showMessageDialog(null, "Usuario logado");
					//variavel de loop de usuario
					boolean opLogin = true;
					do {
						//menu de gerenciamento
						System.out.println("\n\t--- MENU ---\n");
						System.out.println("1 - Gerenciar Usuarios");
						System.out.println("2 - Gerenciar Pacientes 	");
						System.out.println("0 - Sair");
						int opLogUser = sc.nextInt();
						switch(opLogUser) {
						case 1:
							boolean opGerenciarUser = true;
							do {
								//lista sempre para mostrar os usuarios do sistema
								UsuarioDAO lista = new UsuarioDAO();
								List<Usuario> usuarios = lista.getList();
								for(Usuario user : usuarios) {
									System.out.println("Usuario : "+user.getId());
									System.out.println("Nome : "+user.getNome());
									System.out.println("Email : "+user.getEmail());
									System.out.println("CPF : "+user.getCpf()+"\n");
								}
								//menu de usuarios
								System.out.println("1 - Editar");
								System.out.println("2 - Remover");
								System.out.println("3 - Adicionar");
								System.out.println("0 - Sair");
								int opGerenciar = sc.nextInt();
								switch(opGerenciar) {
								case 1:
									//editando os usuarios
									Usuario useredit = new Usuario();
									//digita o ID do usuario que deseja editar
									System.out.println("Digite o ID do usuario que deseja editar");
									int idUserEdit = sc.nextInt();
									//passa os dados a serem editados
									useredit.setId(idUserEdit);
									System.out.print("Novo nome : ");
									String nomeUserEdit = sc.next();
									useredit.setNome(nomeUserEdit);
									System.out.print("Novo CPF : ");
									String cpfUserEdit = sc.next();
									useredit.setCpf(cpfUserEdit);
									System.out.print("Novo email : ");
									String emailUserEdit = sc.next();
									useredit.setEmail(emailUserEdit);
									UsuarioDAO edit = new UsuarioDAO();
									edit.edita(useredit);
									break;
								case 2:
									//digita o ID do usuario que deseja remover
									System.out.println("Digite o ID do usuario que deseja remover");
									int idUser = sc.nextInt();
									UsuarioDAO del = new UsuarioDAO();
									Usuario usuario = new Usuario();
									usuario.setId(idUser);
									del.remove(usuario);
									break;
								case 3:
									System.out.println("\n\t--- ADICIONAR USUARIO ---\n"); //adiciona novo usuario
									System.out.print("Nome : ");
									String nomeUser = sc.next();
									System.out.print("CPF : ");
									String cpfUser = sc.next();
									System.out.print("Email : ");
									String emailUser = sc.next();
									System.out.print("Senha : ");
									String senhaUser = sc.next();
									System.out.println("\nDigite nome e senha do administrador"); //deve digitar os dados de usuario ja cadastrado
									System.out.print("Nome do ADM : ");
									String nomeAdm = sc.next();
									System.out.println("Senha do ADM : ");
									String senhaAdm = sc.next();
									boolean loginAdm = UsuarioFacade.getINSTANCE().facadeLogin(nomeAdm, senhaAdm);
									//passa os dados para poder logar
									if(loginAdm!=false) {
										Usuario user = new Usuario(); //o novo usuario � salvo depois de validar os dados de um adm 
										user.setNome(nomeUser);
										user.setCpf(cpfUser);
										user.setEmail(emailUser);
										user.setSenha(senhaUser);
										UsuarioDAO salvaUser = new UsuarioDAO();
										salvaUser.adiciona(user);
									}
									else { //para caso os dados do adm estejam errados
										JOptionPane.showMessageDialog(null, 
										"Usuario não salvo ! \nConsulte o adiministrador do sistema");
									}
									break;
								case 0: //case para sair do menu de usuario
									opGerenciarUser = false;
									break;
								default: //alerta caso seja digitado um valor invalido
									JOptionPane.showMessageDialog(null, "Opção Invalida");
									break;
								}
							}while(opGerenciarUser!=false);
							break;
						case 2:
							boolean gerenPaciente = true;
							do {
								//menu de paciente
								System.out.println("\n\t--- MENU DE PACIENTE ---\n");
								System.out.println("1 - Adicionar Paciente");
								System.out.println("2 - Listar Pacientes");
								System.out.println("3 - Remover Paciente");
								System.out.println("4 - Editar Pacientes");
								System.out.println("0 - Sair");
								int gerenPacienteCase = sc.nextInt();
								switch (gerenPacienteCase) {
								//case para adicionar
								case 1:
									System.out.print("Digite o tipo de paciente [ 1 = adulto / 2 = crianca ] :");
									int tipoAdd = sc.nextInt();
									if(tipoAdd == 1) {
										System.out.print("Nome :");
										String nomePac = sc.next();
										System.out.print("Endereço :");
										String enderecePac = sc.next();
										System.out.print("Idade :");
										String idadePac = sc.next();
										System.out.print("CPF :");
										String cpfPac = sc.next();
										System.out.print("Cartão do SUS :");
										String cartaoSusPac = sc.next();
										ClienteFactoryMethod fc = new ClienteFactoryMethod();
										fc.getCliente(tipoAdd, nomePac, enderecePac, idadePac, cpfPac, cartaoSusPac);
									}
									else if(tipoAdd == 2) {
										System.out.print("Nome :");
										String nomePac = sc.next();
										System.out.print("Endereço :");
										String enderecePac = sc.next();
										System.out.print("Idade :");
										String idadePac = sc.next();
										System.out.print("CPF :");
										String cpfPac = sc.next();
										System.out.print("Cartão de vacina :");
										String cartaoVacina = sc.next();
										ClienteFactoryMethod fc = new ClienteFactoryMethod();
										fc.getCliente(tipoAdd, nomePac, enderecePac, idadePac, cpfPac, cartaoVacina);
									}
									
									break;
								case 2:
									//listando os pacientes
									System.out.println("Digite o tipo de paciente [ 1 = adulto / 2 = crianca ]");
									int tipoList = sc.nextInt();
									if(tipoList == 1) {
										AdultoDAO lista = new AdultoDAO();
										List<Adulto> pacAdultos = lista.getList();
										for(Adulto pac : pacAdultos) {
											System.out.println("Paciente : " +pac.getId());
											System.out.println("Nome : "+pac.getNome());
											System.out.println("Idade : "+pac.getIdade());
											System.out.println("Cartão do sus : "+pac.getNumeroCartaoDoSUS()+"\n");
										}
									}
									else if(tipoList == 2) {
										CriancaDAO listaCrianca = new CriancaDAO();
										List<Crianca> pacCrianca = listaCrianca.getList();
										for(Crianca pac : pacCrianca) {
											System.out.println("Paciente : "+pac.getId());
											System.out.println("Nome : "+pac.getNome());
											System.out.println("Idade : "+pac.getIdade());
											System.out.println("Cartão de Vacina : "+pac.getNumeroCartaoDeVacina()+"\n");
										}
									}
									break;
								case 3:
									//remover pacientes
									System.out.println("Digite o tipo de paciente [ 1 = adulto / 2 = crianca ]");
									int tipoRemove = sc.nextInt();
									if(tipoRemove == 1) {
										System.out.println("Digite o ID do paciente que deseja remover");
										int idPac = sc.nextInt();
										AdultoDAO del = new AdultoDAO();
										Adulto adulto = new Adulto();
										adulto.setId(idPac);
										del.remove(adulto);
									}
									else if(tipoRemove == 2) {
										System.out.println("Digite o ID do paciente que deseja remover");
										int idPac = sc.nextInt();
										CriancaDAO del = new CriancaDAO();
										Crianca adulto = new  Crianca();
										adulto.setId(idPac);
										del.remove(adulto);
									}
									break;
								case 4:
									//aditando os pacientes 
									System.out.println("Digite o tipo de paciente [ 1 = adulto / 2 = crianca ]");
									int tipoEdita = sc.nextInt();
									if(tipoEdita == 1) {
										Adulto adultoEdita = new Adulto();
										System.out.println("Digite o ID do paciente que deseja editar");
										int idEdit = sc.nextInt();
										adultoEdita.setId(idEdit);
										System.out.print("Nome :");
										String nomeEdit = sc.next();
										adultoEdita.setNome(nomeEdit);
										System.out.print("Endereço :");
										String endereceEdit = sc.next();
										adultoEdita.setEndereco(endereceEdit);
										System.out.print("Idade :");
										String idadeEdit = sc.next();
										adultoEdita.setIdade(idadeEdit);
										System.out.print("CPF :");
										String cpfEdit = sc.next();
										adultoEdita.setCpf(cpfEdit);
										System.out.print("Cartão do SUS :");
										String cartaoEdit = sc.next();
										adultoEdita.setNumeroCartaoDoSUS(cartaoEdit);
										AdultoDAO adultoEditaDAO = new AdultoDAO();
										adultoEditaDAO.edita(adultoEdita);
									}
									else if(tipoEdita == 2) {
										Crianca criancaEdita = new Crianca();
										System.out.println("Digite o ID do paciente que deseja editar");
										int idEdit = sc.nextInt();
										criancaEdita.setId(idEdit);
										System.out.print("Nome :");
										String nomeEdit = sc.next();
										criancaEdita.setNome(nomeEdit);
										System.out.print("Endereço :");
										String endereceEdit = sc.next();
										criancaEdita.setEndereco(endereceEdit);
										System.out.print("Idade :");
										String idadeEdit = sc.next();
										criancaEdita.setIdade(idadeEdit);
										System.out.print("CPF :");
										String cpfEdit = sc.next();
										criancaEdita.setCpf(cpfEdit);
										System.out.print("Cartão de vacina :");
										String cartaoEdit = sc.next();
										criancaEdita.setNumeroCartaoDeVacina(cartaoEdit);
										CriancaDAO criancaEditaDAO = new CriancaDAO();
										criancaEditaDAO.edita(criancaEdita);
									}
									break;
								case 0:
									gerenPaciente = false;
									break;
								default:
									break;
								}
							}while(gerenPaciente!=false);
						break;
						//case para sair do menu de gerenciamento
						case 0:
							opLogin=false;
							break;
						//alerta caso seja digitado um valor invalido
						default:
							JOptionPane.showMessageDialog(null, "Opção Invalida");
						}
					}while(opLogin!=false);
				}
				//alerta dado caso o login de usuairo esteja errado
				else {
					JOptionPane.showMessageDialog(null, "Usuario nao cadastrado ou dados incorretos !");
				}
				break;
			//case para fechar o programa do menu principal
			case 0:
				op=false;
				break;
			//alerta caso seja digitado um valor invalido
			default:
				JOptionPane.showMessageDialog(null, "Opção Invalida");
				op=true;
			}
		}while(op!=false);
	}
}