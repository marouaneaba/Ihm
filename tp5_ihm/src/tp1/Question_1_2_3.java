/*
 * ABAKARIM MAROUANE
 * Université Lille 1
 * Sience Technologie 
 */
package tp1;

import fr.lri.swingstates.canvas.*;
import fr.lri.swingstates.canvas.Canvas;
import fr.lri.swingstates.canvas.CShape;
import fr.lri.swingstates.canvas.CText;
import fr.lri.swingstates.debug.StateMachineEvent;
import fr.lri.swingstates.debug.StateMachineEventListener;
import fr.lri.swingstates.debug.StateMachineVisualization;
import fr.lri.swingstates.sm.State;
import fr.lri.swingstates.sm.StateMachine;
import fr.lri.swingstates.sm.Transition;
import fr.lri.swingstates.sm.transitions.Enter;
import fr.lri.swingstates.sm.transitions.Press;
import fr.lri.swingstates.sm.transitions.Release;
import fr.lri.swingstates.sm.transitions.TimeOut;
import java.awt.BasicStroke;
import java.awt.Color;

import javax.swing.JFrame;
import java.awt.Font;
import java.awt.Paint;

/**
 * @author Nicolas Roussel (roussel@lri.fr)
 *
 */
public class Question_1_2_3 {

    private CText label;
    private CRectangle rect;
    private CStateMachine sm;

    public Question_1_2_3(Canvas canvas, String text) {

        CExtensionalTag selected = new CExtensionalTag(canvas) {
            public void added(CShape s) {
                s.setOutlined(true).setStroke(new BasicStroke(4));
                
            }

            public void removed(CShape s) {
                s.setOutlined(true).setStroke(new BasicStroke(1));
            }
        };

        CExtensionalTag color = new CExtensionalTag(canvas) {
            Paint myColor;

            public void added(CShape s) {
                myColor = s.getFillPaint();
                s.setFillPaint(Color.YELLOW);
            }

            public void removed(CShape s) {
                s.setFillPaint(myColor);
            }
        };
        rect = canvas.newRectangle(25, 25, 50, 30);
        rect.setFillPaint(Color.white);
        label = canvas.newText(-20, 5, text, new Font("verdana", Font.PLAIN, 12));
        rect.setDrawable(true);
        
        label.addChild(rect);

        rect.translateBy(-50, -25);

        sm = new CStateMachine() {
            Paint initColor;
            public State idle = new State() {
                Transition press = new CStateMachine.EnterOnShape(">> in"){
                    public void action() {
                    rect.addTag(selected);
                    }
                };
            };
            public State in = new State() {
                Transition leaveShape = new CStateMachine.LeaveOnShape(">> idle") {
                    public void action() {
                        rect.removeTag(selected);
                        rect.removeTag(color);
                    }
                };
                
                Transition trapress = new CStateMachine.PressOnShape(">> pressed") {
                    public void action() {
                        rect.removeTag(selected);
                        rect.addTag(color);
                    }
                };
            };
            public State pressed = new State() {
               
                
                Transition repress = new ReleaseOnShape(">> in") {
                    public void action() {
                        rect.addTag(selected);
                        rect.removeTag(color);
                    }
                };
                Transition leaveShape = new LeaveOnShape(">> out") {
                    public void action() {
                        rect.removeTag(color);
                        rect.removeTag(selected);
                    }
                };
                
                
            };
            
            public State out = new State() {
                Transition press = new EnterOnShape(">> pressed"){
                    public void action() {
                    rect.addTag(color);
                    }
                };
                
                Transition pess = new Release(">> idle"){
                    public void action() {
                    rect.removeTag(selected);
                    rect.removeTag(color);
                    }
                };
            };
        };
        sm.attachTo(canvas);
//sm.attachTo(rect);
//canvas.above(rect);

        sm.addStateMachineListener(new StateMachineEventListener() {
            public void smStateChanged(StateMachineEvent e) {
                System.out.println("State changed from " + e.getPreviousState().getName() + " to " + e.getCurrentState().getName() + "\n");
            }

            public void smStateLooped(StateMachineEvent e) {
                System.out.println("State looped on \n" + e.getCurrentState().getName());
            }

            public void smSuspended(StateMachineEvent e) {
                System.out.println("State machine suspended\n" + e);
            }

            public void smResumed(StateMachineEvent e) {
                System.out.println("State machine resumed\n" + e);
            }

            public void smReset(StateMachineEvent e) {
                System.out.println("State machine reset\n" + e);
            }

            public void smInited(StateMachineEvent e) {
                System.out.println("State machine inited\n" + e);
            }
        });

    }

    public CStateMachine getState() {
		return sm;
	}
    
    public void action() {
        System.out.println("ACTION!");
    }

    public CShape getShape() {
        return label;
    }

    static public void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Canvas canvas = new Canvas(200, 200);
        frame.getContentPane().add(canvas);
        frame.pack();
        frame.setVisible(true);

        Question_1_2_3 simple = new Question_1_2_3(canvas, "simple");
        simple.getShape().translateBy(100, 50);

        
        CStateMachine MyState = simple.getState();
		MyState.attachTo(canvas);
		
		JFrame StateFrame = new JFrame();
		StateFrame.setLocation(250, 250);
		StateFrame.getContentPane().add(new StateMachineVisualization(MyState));
		StateFrame.pack();
		StateFrame.setVisible(true);
    }

}
