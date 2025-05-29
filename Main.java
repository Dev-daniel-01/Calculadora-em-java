import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean continuar = true;

        while (continuar) {
            System.out.println("\nOlá! Seja bem-vindo à Calculadora Balofa");
            System.out.println("Operações disponíveis:");
            System.out.println(" +  Soma");
            System.out.println(" -  Subtração");
            System.out.println(" *  Multiplicação");
            System.out.println(" /  Divisão");
            System.out.println(" r  Raiz Quadrada (do primeiro número)");
            System.out.println(" b  Bhaskara (equação do 2º grau)");
            System.out.print("Escolha a operação: ");
            char operador = scanner.next().charAt(0);

            // Validação explícita do operador
            if (operador != '+' && operador != '-' && operador != '*' && operador != '/' &&
                    operador != 'r' && operador != 'R' && operador != 'b' && operador != 'B') {
                System.out.println("Erro: Operador inválido! Tente novamente.");
                continue;  // volta para o início do loop sem executar nada mais
            }

            try {
                if (operador == 'r' || operador == 'R') {
                    System.out.print("Insira o número para calcular a raiz quadrada: ");
                    float num = scanner.nextFloat();
                    if (num < 0) {
                        System.out.println("Erro: Raiz quadrada de número negativo não é permitida.");
                    } else {
                        System.out.printf("Raiz quadrada de %.2f é %.2f\n", num, Math.sqrt(num));
                    }
                } else if (operador == 'b' || operador == 'B') {
                    System.out.println("--- Bhaskara ---");
                    System.out.print("Coeficiente A: ");
                    double a = scanner.nextDouble();
                    System.out.print("Coeficiente B: ");
                    double b = scanner.nextDouble();
                    System.out.print("Coeficiente C: ");
                    double c = scanner.nextDouble();
                    calcularBhaskara(a, b, c);
                } else {
                    System.out.print("Insira o primeiro número: ");
                    float num1 = scanner.nextFloat();
                    System.out.print("Insira o segundo número: ");
                    float num2 = scanner.nextFloat();

                    float resultado = calcular(num1, num2, operador);
                    System.out.printf("Resultado: %.2f %c %.2f = %.2f\n", num1, operador, num2, resultado);
                }
            } catch (ErroPorZero | OperadorInvalido e) {
                System.out.println("Erro: " + e.getMessage());
            } catch (Exception e) {
                System.out.println("Erro: Entrada inválida. Por favor, insira os dados corretamente.");
                scanner.nextLine(); // Limpa buffer em caso de erro
            }

            System.out.print("Deseja realizar outro cálculo? (s/n): ");
            char resposta = scanner.next().charAt(0);
            continuar = (resposta == 's' || resposta == 'S');
        }

        System.out.println("Obrigado por usar a Calculadora Balofa! Até logo!");
        scanner.close();
    }

    public static float calcular(float num1, float num2, char operador) {
        switch (operador) {
            case '+': return num1 + num2;
            case '-': return num1 - num2;
            case '*': return num1 * num2;
            case '/':
                if (num2 == 0) {
                    throw new ErroPorZero("Erro: divisão por zero!");
                }
                return num1 / num2;
            default:
                throw new OperadorInvalido("Operador inválido: " + operador);
        }
    }

    public static void calcularBhaskara(double a, double b, double c) {
        if (a == 0) {
            System.out.println("Erro: O coeficiente A não pode ser zero em uma equação do 2º grau.");
            return;
        }

        double delta = Math.pow(b, 2) - 4 * a * c;

        System.out.printf("Delta: %.2f\n", delta);

        if (delta < 0) {
            System.out.println("A equação não possui raízes reais.");
        } else {
            double x1 = (-b + Math.sqrt(delta)) / (2 * a);
            double x2 = (-b - Math.sqrt(delta)) / (2 * a);

            if (delta == 0) {
                System.out.printf("A equação possui uma raiz real: x = %.2f\n", x1);
            } else {
                System.out.printf("As raízes da equação são: x1 = %.2f, x2 = %.2f\n", x1, x2);
            }
        }
    }
}

class ErroPorZero extends RuntimeException {
    public ErroPorZero(String mensagem) {
        super(mensagem);
    }
}

class OperadorInvalido extends RuntimeException {
    public OperadorInvalido(String mensagem) {
        super(mensagem);
    }
}
