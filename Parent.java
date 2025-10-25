public class Parent {

    public int x = 0;

    public Parent(int x) {

        this.x = x;

    }


    public class InnerChild extends Parent {

        public int x = 1;

        public InnerChild(int x) {

            super(2 * x);

            this.x = x;

        }

        public void innerMethod(int x) {

            outerMethod(x);

            System.out.println(x);

            System.out.println(this.x);

            System.out.println(super.x);

            System.out.println(Parent.this.x);

        }

    }


    public void outerMethod(int x) {

        System.out.println(x);

        System.out.println(this.x);

    }

    public static void main(String[] args) {

        int x = 37;

        Parent p = new Parent(x);

        x = 6;

        Parent c1 = p.new InnerChild(x + 1);

        InnerChild c2 = p.new InnerChild(x - 1);

        p.outerMethod(2);

        c1.outerMethod(3);

        c2.innerMethod(4);

    }
}