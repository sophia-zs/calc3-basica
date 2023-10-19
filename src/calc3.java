import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

public class calc3 extends JFrame implements ActionListener {

    private JTextField pantalla;
    private JButton[] botonesNumeros;
    private JButton[] botonesOperadores;
    private String[] operadores = { "+", "-", "*", "/" };
    private float num1, num2;
    private String operador = "";

    public calc3() {
        setLayout(null);
        setTitle("Calculadora");
        int colorHexFondo = 0x16506A;
        Color colorFondo = new Color(colorHexFondo);
        getContentPane().setBackground(colorFondo);

        pantalla = new JTextField();
        pantalla.setBounds(10, 10, 270, 55);
        pantalla.setBackground(Color.darkGray);
        pantalla.setHorizontalAlignment(SwingConstants.RIGHT);
        Font fontPantalla = new Font("Arial", Font.BOLD, 28);
        pantalla.setFont(fontPantalla);
        pantalla.setForeground(Color.GREEN);
        add(pantalla);

        // Inicialización de los botones numéricos
        botonesNumeros = new JButton[16]; // Aumentado el tamaño para incluir 'C', '0' y '.'

        for (int i = 0; i < 16; i++) {
            if (i == 0) {
                botonesNumeros[i] = new JButton("7");
            } else if (i == 1) {
                botonesNumeros[i] = new JButton("8");
            } else if (i == 2) {
                botonesNumeros[i] = new JButton("9");
            } else if (i == 3) {
                botonesNumeros[i] = new JButton("/");
            } else if (i == 4) {
                botonesNumeros[i] = new JButton("4");
            } else if (i == 5) {
                botonesNumeros[i] = new JButton("5");
            } else if (i == 6) {
                botonesNumeros[i] = new JButton("6");
            } else if (i == 7) {
                botonesNumeros[i] = new JButton("*");
            } else if (i == 8) {
                botonesNumeros[i] = new JButton("1");
            } else if (i == 9) {
                botonesNumeros[i] = new JButton("2");
            } else if (i == 10) {
                botonesNumeros[i] = new JButton("3");
            } else if (i == 11) {
                botonesNumeros[i] = new JButton("-");
            } else if (i == 12) {
                botonesNumeros[i] = new JButton("C");
            } else if (i == 13) {
                botonesNumeros[i] = new JButton("0");
            } else if (i == 14) {
                botonesNumeros[i] = new JButton(".");
            } else if (i == 15) {
                botonesNumeros[i] = new JButton("*");
            }

            int columnaBoton = i % 4;
            int filaBoton = i / 4;
            botonesNumeros[i].setBounds(10 + columnaBoton * 70, 125 + filaBoton * 70, 60, 60);
            Font fontNumero = new Font("Arial", Font.BOLD, 18);
            botonesNumeros[i].setFont(fontNumero);
            add(botonesNumeros[i]);
            botonesNumeros[i].addActionListener(this);
        }

        botonesOperadores = new JButton[4];

        String[] operadores = { "/", "*", "-", "+" };
        int filaOperador = 1;

        for (int i = 0; i < 4; i++) {
            botonesOperadores[i] = new JButton(operadores[i]);
            botonesOperadores[i].setBounds(220, 55 + filaOperador * 70, 60, 60);
            Font fontOperador = new Font("Arial", Font.BOLD, 20);
            botonesOperadores[i].setFont(fontOperador);
            add(botonesOperadores[i]);
            botonesOperadores[i].addActionListener(this);

            filaOperador++;
        }

        // Botón igual
        JButton botonIgual = new JButton("Resultado");
        botonIgual.setBounds(10, 70, 270, 50);
        Font fontIgual = new Font("Arial", Font.BOLD, 20);
        botonIgual.setFont(fontIgual);
        add(botonIgual);
        botonIgual.addActionListener(this);

        // Configuración del JFrame
        setBounds(0, 0, 310, 445);
        setVisible(true);
        setResizable(false);
        setLocationRelativeTo(null);
    }

    public void actionPerformed(ActionEvent e) {
        String textoBoton = ((JButton) e.getSource()).getText();

        // Verifica si el evento proviene de algún botón numérico
        for (int i = 0; i < 16; i++) {
            if (textoBoton.equals(Integer.toString(i))) {
                pantalla.setText(pantalla.getText() + i);
                return;
            }
        }

        // Verifica si el evento proviene de algún botón de operador
        for (int i = 0; i < 4; i++) {
            if (textoBoton.equals(operadores[i])) {
                num1 = Float.parseFloat(pantalla.getText());
                operador = operadores[i];
                pantalla.setText(pantalla.getText() + operador);
                return;
            }
        }

        if (textoBoton.equals("C")) {
            pantalla.setText("");
        } else if (textoBoton.equals("Resultado")) {
            // Realiza el cálculo y muestra el resultado
            if (!operador.isEmpty()) {
                num2 = Float.parseFloat(pantalla.getText().substring(pantalla.getText().indexOf(operador) + 1));
                float resultado = 0;

                switch (operador) {
                    case "+":
                        resultado = num1 + num2;
                        break;
                    case "-":
                        resultado = num1 - num2;
                        break;
                    case "*":
                        resultado = num1 * num2;
                        break;
                    case "/":
                        if (num2 != 0) {
                            resultado = num1 / num2;
                        } else {
                            pantalla.setText("Infinito, no aplica");
                            return;
                        }
                        break;
                }

                pantalla.setText(sincero(resultado));
            }
        } else {
            pantalla.setText(pantalla.getText() + textoBoton);
        }
    }

    public String sincero(float resultado) {
        String retorno = Float.toString(resultado);
        if (resultado % 1 == 0) {
            retorno = retorno.substring(0, retorno.length() - 2);
        }
        return retorno;
    }

    public static void main(String args[]) {
        new calc3();
    }
}
