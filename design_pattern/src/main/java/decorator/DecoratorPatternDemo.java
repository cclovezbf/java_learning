package decorator;

import bean.*;

public class DecoratorPatternDemo {
    public static void main(String[] args) {

        Shape circle = new Circle();
        ShapeDecorator redCircle = new RedShapeDecorator(new Circle());
        ShapeDecorator redRectangle = new RedShapeDecorator(new Rectangle());
        //bean.Shape redCircle = new bean.RedShapeDecorator(new bean.Circle());
        //bean.Shape redRectangle = new bean.RedShapeDecorator(new bean.Rectangle());
        System.out.println("简单的圆");
        circle.draw();

        System.out.println("\n红圆");
        redCircle.draw();

        System.out.println("\n红色长方形");
        redRectangle.draw();
    }
}