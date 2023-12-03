package excecao;


    public class SIException extends Exception{


        private double saldo;


        public SIException(double saldo ){
            super("\nSaldo Inválido");

            this.saldo = saldo;

        }

        public double getSaldo(){
            return saldo;
        }

    }
