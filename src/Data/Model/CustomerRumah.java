package Data.Model;

public class CustomerRumah {
    private String NIK, nama, email, telp;

    public CustomerRumah(String NIK, String nama, String email, String telp) {
        this.NIK = NIK;
        this.nama = nama;
        this.email = email;
        this.telp = telp;
    }

    public String getNIK() {
        return NIK;
    }

    public void setNIK(String nIK) {
        NIK = nIK;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelp() {
        return telp;
    }

    public void setTelp(String telp) {
        this.telp = telp;
    }

}
