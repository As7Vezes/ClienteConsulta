public class Consulta {
        Paciente paciente;
        String dia;
        String hora;
        String especialidade;

        public Consulta(Paciente paciente, String dia, String hora, String especialidade) {
            this.paciente = paciente;
            this.dia = dia;
            this.hora = hora;
            this.especialidade = especialidade;
        }
}
