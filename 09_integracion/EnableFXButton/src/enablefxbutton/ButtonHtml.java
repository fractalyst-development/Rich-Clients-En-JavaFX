/*
 * Copyright (c) 1995, 2014, Oracle and/or its affiliates. All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 *   - Redistributions of source code must retain the above copyright
 *     notice, this list of conditions and the following disclaimer.
 *
 *   - Redistributions in binary form must reproduce the above copyright
 *     notice, this list of conditions and the following disclaimer in the
 *     documentation and/or other materials provided with the distribution.
 *
 *   - Neither the name of Oracle or the names of its
 *     contributors may be used to endorse or promote products derived
 *     from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS
 * IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR
 * PURPOSE ARE DISCLAIMED.  IN NO EVENT SHALL THE COPYRIGHT OWNER OR
 * CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL,
 * EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,
 * PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR
 * PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF
 * LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
 * NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */ 

package enablefxbutton;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import javafx.application.Platform;
import javax.swing.AbstractButton;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.ImageIcon;

/* 
 * ButtonHtmlDemo.java uses the following files:
 *   images/right.gif
 *   images/left.gif
 */
public class ButtonHtml extends JPanel
                            implements ActionListener {
    protected JButton b1, b3;

    public ButtonHtml() {
        ImageIcon buttonIcon = createImageIcon("images/down.gif");      
            
        b1 = new JButton("<html><center><b><u>D</u>isable</b><br>"
                         + "<font color=#ffffdd>FX button</font>", 
                         buttonIcon);
        Font font = b1.getFont().deriveFont(Font.PLAIN);
        b1.setFont(font);
        b1.setVerticalTextPosition(AbstractButton.CENTER);
        b1.setHorizontalTextPosition(AbstractButton.LEADING); //aka LEFT, for left-to-right locales
        b1.setMnemonic(KeyEvent.VK_D);
        b1.setActionCommand("disable");
                          
             
        b3 = new JButton("<html><center><b><u>E</u>nable</b><br>"
                         + "<font color=#ffffdd>FX button</font>", 
                         buttonIcon);
        b3.setFont(font);
        //Use the default text position of CENTER, TRAILING (RIGHT).
        b3.setMnemonic(KeyEvent.VK_E);
        b3.setActionCommand("enable");
        b3.setEnabled(false);

        //Listen for actions on buttons 1 and 3.
        b1.addActionListener(this);
        b3.addActionListener(this);

        b1.setToolTipText("Click this button to disable the FX button.");
        b3.setToolTipText("Click this button to enable the FX button.");

        //Add Components to this container, using the default FlowLayout.
        add(b1);
        add(b3);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if ("disable".equals(e.getActionCommand())) {
            Platform.runLater(() -> {
                EnableFXButton.fxbutton.setDisable(true);
            });

            
            b1.setEnabled(false);
            b3.setEnabled(true);
        } else {
            Platform.runLater(() -> {
                EnableFXButton.fxbutton.setDisable(false);
            });
            b1.setEnabled(true);
            b3.setEnabled(false);
        }
    }

    /** Returns an ImageIcon, or null if the path was invalid.
     * @param path
     * @return  */
    protected static ImageIcon createImageIcon(String path) {
        java.net.URL imgURL = ButtonHtml.class.getResource(path);
        if (imgURL != null) {
            return new ImageIcon(imgURL);
        } else {
            System.err.println("Couldn't find file: " + path);
            return null;
        }
    }
  
}
