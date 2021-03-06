package webcart;

import java.io.IOException;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class Cart {

    protected Map<String, Integer> entries = new HashMap<>();

    protected void added( String reference, int quantity ) {}
    protected void updated( String reference, int quantity ) {}
    protected void deleted( String reference ) {}

    public void addToCart(String reference, int quantity) {

        // Check if entry was in cart
        Integer qte = entries.get( reference );
        if ( qte == null ) {
            // add a new entry
            entries.put( reference, quantity);
            added( reference, quantity );
        }
        else {
            if ( quantity <= 0 ) {
                // remove entry if quantity is lower than 0
                entries.remove(reference);
                deleted(reference);
            }
            else {
                entries.put( reference, quantity );
            }
        }
    }

    public Boolean isEmpty() {
        return this.entries.isEmpty();
    }

    public void print(Writer out) {
        try {
            out.append("<ul>\n");
            for ( Entry<String, Integer> entry : entries.entrySet() ) {
                out.append( String.format("<li>%s (x %03d)</li>\n", entry.getKey(), entry.getValue()) );
            }
            out.append("</ul>\n");
        } catch (IOException e) {}
    }

    public String getHtml() {
        String html = "";
        if (!this.isEmpty()) {
            html += "<ul>\n";
            for ( Entry<String, Integer> entry : entries.entrySet() ) {
                html += String.format("<li>%s (x %03d)</li>\n", entry.getKey(), entry.getValue());
            }
            html += "</ul>\n";
        }
        return html;
    }

    public boolean mayAdd( String reference, int quantity  ) {
        return true;
    }

    @Override
    public String toString() {
        return entries.toString();
    }

}
