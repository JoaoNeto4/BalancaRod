
package br.com.teste;

import jssc.SerialPort;
import jssc.SerialPortException;

/**
 *
 * @author joao
 */
public class TestePeso {
    
    public void capturaPeso(){
        /*
        $ /dev/ttyUSB0
            Arquivo: /dev/ttyUSB0
            Tamanho: 0         	Blocos: 0          bloco de E/S: 4096   arquivo especial de caractere
            Dispositivo: 5h/5d	Inode: 708         Links: 1     Tipo de dispositivo: bc,0
            Acesso: (0660/crw-rw----)  Uid: (    0/    root)   Gid: (   20/ dialout)
            Acesso: 2022-09-23 09:50:22.455834339 -0300
            Modificação: 2022-09-23 09:50:22.455834339 -0300
            Alteração: 2022-09-23 09:50:22.455834339 -0300
        
        sudo usermod -a -G dialout < nome de usuário >
        
        ou 
        
        sudo chmod 777 /dev/ttyUSB0
        */
        
        SerialPort serialPort2 = new SerialPort("COM1");
        SerialPort serialPort = new SerialPort("/dev/ttyUSB0");
            try {
                System.out.println("Porta aberta: " + serialPort.openPort());

                    System.out.println("Parametros de config: " + serialPort.setParams(9600, 8, 2, 0));
                    System.out.println("Sucesso de escrita na porta: " + serialPort.writeBytes(new byte[]{0x04}));
                    byte[] buffer = serialPort.readBytes(46);//Read 10 bytes from serial port
                    System.out.println(new String(buffer));
                    System.out.println("Porta fechada: " + serialPort.closePort());
                
            } catch (SerialPortException ex) {
                System.out.println(ex);
        }
    }
    
    public static void main(String[] args) {
        TestePeso t =new TestePeso();
        t.capturaPeso();
    }
}
