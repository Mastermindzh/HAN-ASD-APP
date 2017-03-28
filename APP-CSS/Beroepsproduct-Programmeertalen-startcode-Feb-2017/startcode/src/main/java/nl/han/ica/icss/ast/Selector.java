package nl.han.ica.icss.ast;

public class Selector extends ASTNode {
	public String tag;
	public String cls;
	public String id;

    public Selector(String value){
        assignValue(value);
    }

    public Selector(){

    }


    private void assignValue(String value){
        if(value != null) {
            switch (value.indexOf(0)){
                case '#':
                    id = value;
                    break;
                case '.':
                    cls = value;
                    break;
                default:
                    tag = value;
                    break;
            }
        }

    }

    @Override
    public String getNodeLabel() {
        return "Selector";
    }
    @Override
    public String toString() {
        if(tag != null)
            return tag;
		else if(cls != null)
		    return cls;
		else
            return id;
    }
}
