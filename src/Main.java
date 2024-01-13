import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    static ArrayList<Paciente> pacientesCadastrados = new ArrayList<>();
    static ArrayList<Consulta> agendamentos = new ArrayList<>();
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int opcao;

        do {
            System.out.println("\nMenu Principal:");
            System.out.println("1. Cadastrar um paciente");
            System.out.println("2. Marcar consultas");
            System.out.println("3. Cancelar consultas");
            System.out.println("4. Sair");

            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1:
                    cadastrarPaciente();
                    break;
                case 2:
                    marcarConsulta();
                    break;
                case 3:
                    cancelarConsulta();
                    break;
                case 4:
                    System.out.println("Encerrando o programa.");
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }

        } while (opcao != 4);
    }

    static void cadastrarPaciente() {
        System.out.print("Digite o nome do paciente: ");
        String nome = scanner.nextLine();

        System.out.print("Digite o telefone do paciente: ");
        String telefone = scanner.nextLine();

        if (buscarPacientePorTelefone(telefone) != null) {
            System.out.println("Paciente já cadastrado!");
        } else {
            Paciente paciente = new Paciente(nome, telefone);
            pacientesCadastrados.add(paciente);
            System.out.println("Paciente cadastrado com sucesso!");
        }
    }

    static void marcarConsulta() {
        if (pacientesCadastrados.isEmpty()) {
            System.out.println("Não há pacientes cadastrados. Cadastre um paciente antes de marcar uma consulta.");
            return;
        }

        System.out.println("Lista de Pacientes Cadastrados:");
        for (int i = 0; i < pacientesCadastrados.size(); i++) {
            System.out.println((i + 1) + ". " + pacientesCadastrados.get(i).nome);
        }

        System.out.print("Escolha o número correspondente ao paciente: ");
        int indicePaciente = scanner.nextInt() - 1;

        if (indicePaciente < 0 || indicePaciente >= pacientesCadastrados.size()) {
            System.out.println("Número de paciente inválido. Tente novamente.");
            return;
        }

        Paciente paciente = pacientesCadastrados.get(indicePaciente);

        System.out.print("Digite o dia da consulta: ");
        String dia = scanner.next();

        System.out.print("Digite a hora da consulta: ");
        String hora = scanner.next();

        System.out.print("Digite a especialidade da consulta: ");
        String especialidade = scanner.next();

        if (verificarDisponibilidadeConsulta(dia, hora)) {
            System.out.println("Essa data e hora já estão agendadas. Escolha outro horário.");
        } else {
            Consulta consulta = new Consulta(paciente, dia, hora, especialidade);
            agendamentos.add(consulta);
            System.out.println("Consulta agendada com sucesso!");
        }
    }

    static void cancelarConsulta() {
        if (agendamentos.isEmpty()) {
            System.out.println("Não há consultas agendadas para cancelar.");
            return;
        }

        System.out.println("Lista de Consultas Agendadas:");
        for (int i = 0; i < agendamentos.size(); i++) {
            Consulta consulta = agendamentos.get(i);
            System.out.println((i + 1) + ". Paciente: " + consulta.paciente.nome +
                    ", Dia: " + consulta.dia + ", Hora: " + consulta.hora + ", Especialidade: " + consulta.especialidade);
        }

        System.out.print("Escolha o número correspondente à consulta que deseja cancelar: ");
        int indiceConsulta = scanner.nextInt() - 1;

        if (indiceConsulta < 0 || indiceConsulta >= agendamentos.size()) {
            System.out.println("Número de consulta inválido. Tente novamente.");
            return;
        }

        Consulta consultaCancelada = agendamentos.remove(indiceConsulta);
        System.out.println("Consulta cancelada com sucesso! Detalhes da consulta cancelada:");
        System.out.println("Paciente: " + consultaCancelada.paciente.nome +
                ", Dia: " + consultaCancelada.dia + ", Hora: " + consultaCancelada.hora +
                ", Especialidade: " + consultaCancelada.especialidade);
    }

    static Paciente buscarPacientePorTelefone(String telefone) {
        for (Paciente paciente : pacientesCadastrados) {
            if (paciente.telefone.equals(telefone)) {
                return paciente;
            }
        }
        return null;
    }

    static boolean verificarDisponibilidadeConsulta(String dia, String hora) {
        for (Consulta consulta : agendamentos) {
            if (consulta.dia.equals(dia) && consulta.hora.equals(hora)) {
                return true;
            }
        }
        return false;
    }
}