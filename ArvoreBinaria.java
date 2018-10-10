import java.*;

/**
  * TP3/Q-1 - Arvore Binaria
  * @author: Ana Flavia Dias Rodrigues
*/
class Partida{
  //metodos privados
  private String copa;
  private int placar1;
  private int placar2;
  private int dia;
  private int mes;
  private String etapa;
  private String time1;
  private String time2;
  private String local;

  /**
   * Primeiro Construtor para inicializar os metodos privados
  */
  public Partida(){
    copa = "";
    placar1 = 0;
    placar2 = 0;
    dia = 0;
    mes = 0;
    etapa = "";
    time1 = "";
    time2 = "";
    local = "";
  }//fim construtor 1

  /**
   * Segundo Construtor para atribuir valores aos metodos privados
   * @param atributos da classe Partida
   */
  public Partida(String copa, int placar1, int placar2, int dia, int mes, String etapa, String time1, String time2, String local){
    this.copa = copa;
    this.placar1 = placar1;
    this.placar2 = placar2;
    this.dia = dia;
    this.mes = mes;
    this.etapa = etapa;
    this.time1 = time1;
    this.time2 = time2;
    this.local = local;
  }

  /**
   * Metodo que protege a classe Objeto
   * @return nova instancia da classe
   */
  public Partida clone(){
    return new Partida(getCopa(), getPlacar1(), getPlacar2(), getDia(), getMes(), getEtapa(), getTime1(), getTime2(),
     getLocal());
  }//fim clone

  /**
   * Metodos GET e SET
   * GETTERS
   * @return os atributos
   * SETTERS
   * @param atributos
   */
  public String getCopa(){
   return copa;
  }//fim getEtapa
  public void setCopa(String copa){
   this.copa = copa;
  }//fim setEtapa
  public int getPlacar1(){
    return placar1;
  }//fim getPlacar1
  public void setPlacar1(int placar1){
    this.placar1 = placar1;
  }//fim setPlacar1

  public int getPlacar2(){
    return placar2;
  }//fim getPlacar2
  public void setPlacar2(int placar2){
    this.placar2 = placar2;
  }//fim setPlacar2

  public int getDia(){
    return dia;
  }//fim getDia
  public void setDia(int dia){
    this.dia = dia;
  }//fim setDia

  public int getMes(){
    return mes;
  }//fim getMes
  public void setMes(int mes){
    this.mes = mes;
  }//fim setMes

  public String getEtapa(){
    return etapa;
  }//fim getEtapa
  public void setEtapa(String etapa){
    this.etapa = etapa;
  }//fim setEtapa

  public String getTime1(){
    return time1;
  }//fim getTime1
  public void setTime1(String time1){
    this.time1 = time1;
  }//fim setTime1

  public String getTime2(){
    return time2;
  }//fim getTime2
  public void setTime2(String time2){
    this.time2 = time2;
  }//fim setTime2

  public String getLocal(){
    return local;
  }//fim getLocal
  public void setLocal(String local){
    this.local = local;
  }//fim setLocal

  /**
   * Metodo que imprime o que foi lido na url
   */
  public void imprimeHTML(){
    MyIO.println("[COPA " + getCopa() +"] - " +getEtapa()+" - "+getDia()+"/"+getMes()+" - "+getTime1()+"("+getPlacar1()+") x ("+getPlacar2()+") "+getTime2()+" - "+getLocal());
  }

  /**
    * Metodo para remover tags
    * @param LinhaEntrada String
    * @return resp String
    */
  public String removeTags(String linhaEntrada){
    String resp = "";

    for (int i = 0; i < linhaEntrada.length(); i++){

      while(i < linhaEntrada.length() && linhaEntrada.charAt(i) == '<'){

        for (i++; linhaEntrada.charAt(i) != '>'; i++);
        i++;

        while(i < linhaEntrada.length() && linhaEntrada.charAt(i) == '&'){
          for (i++; linhaEntrada.charAt(i) != ';'; i++);
            i++;
          }
        }

        while(i < linhaEntrada.length() && linhaEntrada.charAt(i) == '&'){
          for (i++; linhaEntrada.charAt(i) != ';'; i++);
            i++;
            resp += ' ';
          }

          if(i < linhaEntrada.length()){
            resp += linhaEntrada.charAt(i);
          }
        }

        while(resp.length() > 0 && resp.charAt(0) == ' '){
          resp = resp.substring(1);
        }
        return resp;
  }//fim removeTags

  /**
  * Metodo que abre e le o HTML
  * @param html String
  */
  public void lerHTML (int ano, Ranking ranking) throws Exception {
    //Partida[] partida = new Partida[1000];
    Time t1 = new Time();//joao
    Time t2 = new Time();//maria

    String nomeArquivo = "/tmp/copa/" + ano + ".html";
    String linha, etapa = "";
    Partida partida = this;


    Arq.openRead(nomeArquivo, "ISO-8859-1");

    for(linha = Arq.readLine(); linha.contains("<table ") == false && Arq.hasNext() == true; linha = Arq.readLine());
      linha = Arq.readLine();

    do{

    if(linha.contains("bgcolor") == true || linha.replace(" ","").length() == 0){
      for(linha = Arq.readLine(); linha.contains("<td") == false && Arq.hasNext() == true; linha = Arq.readLine());
      etapa = removeTags(linha);
    }

    partida.copa = ano+"";
    partida.etapa = etapa;

    for(linha = Arq.readLine(); linha.contains("<td") == false && Arq.hasNext() == true; linha = Arq.readLine());
    linha = removeTags(linha).replace(" ","");
    partida.dia = (new Integer (linha.substring(0,linha.indexOf('/')))).intValue();
    partida.mes = (new Integer (linha.substring(linha.indexOf('/')+1, linha.length()))).intValue();

    for(linha = Arq.readLine(); linha.contains("<td") == false && Arq.hasNext() == true; linha = Arq.readLine());
    partida.time1 = removeTags(linha);
      
    t1 = new Time();
    t2=new Time();

    time1 = time1.trim();
    
    if(time1.equals("União Soviética")){
        t1.setPais("Rússia");
    }else if(time1.equals("Tchecoslováquia")){
      t1.setPais("República Tcheca");
    }else if(time1.equals("Alemanha Ocidental")){
      t1.setPais("Alemanha");
    }else if(time1.equals("Iugoslávia") || time1.equals("Sérvia e Montenegro")){
      t1.setPais("Sérvia");
    }else{
      t1.setPais(time1);
    }


    for(linha = Arq.readLine(); linha.contains("<td") == false && Arq.hasNext() == true; linha = Arq.readLine());
    linha = removeTags(linha);
    partida.placar1 = (new Integer (linha.substring(0, linha.indexOf("x")).trim())).intValue();
    partida.placar2 = (new Integer (linha.substring(linha.indexOf("x")+1, linha.length()).trim())).intValue();
    
    t1.setGolPro(placar1); 
    t1.setGolContra(placar2); 

    t2.setGolPro(placar2); 
    t2.setGolContra(placar1); 



    for(linha = Arq.readLine(); linha.contains("<td") == false && Arq.hasNext() == true; linha = Arq.readLine());
    partida.time2 = removeTags(linha);
    time2 = time2.trim();
    if(time2.equals("União Soviética")){
      t2.setPais("Rússia");
    }else if(time2.equals("Tchecoslováquia")){
      t2.setPais("República Tcheca");
    }else if(time2.equals("Alemanha Ocidental")){
      t2.setPais("Alemanha");
    }else if(time2.equals("Iugoslávia") || time2.equals("Sérvia e Montenegro")){
      t2.setPais("Sérvia");
    }else{
      t2.setPais(time2);
    }


    t1.setJogos(1);
    t2.setJogos(1);
    if(placar1 > placar2){
      t1.setVitorias(1);
      t1.setNumPontos(3);
      t2.setDerrotas(1);
    
    }else if(placar1 < placar2){
      t1.setDerrotas(1);
      t2.setVitorias(1);
      t2.setNumPontos(3);
    
    }else if(placar1 == placar2){
      t1.setEmpates(1);
      t1.setNumPontos(1);
      t2.setEmpates(1);
      t2.setNumPontos(1);
    }

    t1.setSaldo((placar1 - placar2));
    t2.setSaldo((placar2 - placar1));

    t1.setDoidao(t1.getNumPontos() * 1000 + t1.getGolPro());
    t2.setDoidao(t2.getNumPontos() * 1000 + t2.getGolPro());

    for(linha = Arq.readLine(); linha.contains("<td") == false && Arq.hasNext() == true; linha = Arq.readLine());
    partida.local = removeTags(linha);


    Arq.readLine(); // ler o </tr>
    linha = Arq.readLine();
    t2.setPais(t2.getPais().trim());

    ranking.processa(t1, t2);

    t1 = new Time();
    t2 = new Time();

    } while (linha.contains("000000") == false);
      Arq.close();
  }//fim lerHTML


}//fim classe Partida

//-----------------------------------------------------------------------------------------------------------

/**
  * Classe Time
*/
class Time{
  //metodos privados
  private String pais;
  private int numPontos;
  private int jogos;
  private int vitorias;
  private int empates;
  private int derrotas;
  private int golPro;
  private int golContra;
  private int saldo;
  private int doidao;
  private int contObjetos = 0;

  /**
   * Primeiro Construtor para inicializar os metodos privados
  */
  public Time(){
    pais = "";
    numPontos = 0;
    jogos = 0;
    vitorias = 0;
    empates = 0;
    derrotas = 0;
    golPro = 0;
    golContra = 0;
    saldo = 0;
    doidao = 0;
  }//fim construtor 1

  /**
   * Segundo Construtor para atribuir valores aos metodos privados
   * @param atributos da classe Time
   */
  public Time(String pais, int numPontos, int jogos, int vitorias, int empates, int derrotas, int golPro, int golContra, int saldo, int doidao){
    this.pais = pais;
    this.numPontos = numPontos;
    this.jogos = jogos;
    this.vitorias = vitorias;
    this.empates = empates;
    this.derrotas = derrotas;
    this.golPro = golPro;
    this.golContra = golContra;
    this.saldo = saldo;
    this.doidao = doidao;
  }

  /**
   * Metodo que protege a classe Objeto
   * @return nova instancia da classe
   */
  public Time clone(){
    Time clone = new Time();
    clone.setPais(this.getPais());
    clone.setNumPontos(this.getNumPontos());
    clone.setJogos(this.getJogos());
    clone.setVitorias(this.getVitorias());
    clone.setEmpates(this.getEmpates());
    clone.setDerrotas(this.getDerrotas());
    clone.setGolPro(this.getGolPro());
    clone.setGolContra(this.getGolContra());
    clone.setSaldo(this.getSaldo());
    clone.setDoidao(this.getDoidao());
    return clone;
  }//fim clone

  public void imprimir(){
    MyIO.println(this.getPais() + " pg(" + this.getNumPontos() + ") j(" + this.getJogos() + ") v(" + this.getVitorias() + ") e(" + this.getEmpates() + ") d(" + this.getDerrotas() + ") gp(" + this.getGolPro() + ") gc(" + this.getGolContra() + ") sg(" + this.getSaldo() + ") d(" + this.getDoidao() + ")");
  }

  /**
   * Metodos GET e SET
   * GETTERS
   * @return os atributos
   * SETTERS
   * @param atributos
   */
  public String getPais(){
   return pais;
  }//fim getPais
  public void setPais(String pais){
   this.pais = pais;
  }//fim setPais

  public int getNumPontos(){
   return numPontos;
  }//fim getNumPontos
  public void setNumPontos(int numPontos){
   this.numPontos = numPontos;
  }//fim setEtapa

  public int getJogos(){
   return jogos;
  }//fim getJogos
  public void setJogos(int jogos){
   this.jogos = jogos;
  }//fim setJogos

  public int getVitorias(){
   return vitorias;
  }//fim getVitorias
  public void setVitorias(int vitorias){
   this.vitorias = vitorias;
  }//fim setVitorias

  public int getEmpates(){
   return empates;
  }//fim getEmpates
  public void setEmpates(int empates){
   this.empates = empates;
  }//fim setEmpates

  public int getDerrotas(){
   return derrotas;
  }//fim getDerrotas
  public void setDerrotas(int derrotas){
   this.derrotas = derrotas;
  }//fim setDerrotas

  public int getGolPro(){
   return golPro;
  }//fim getGoPro
  public void setGolPro(int golPro){
   this.golPro = golPro;
  }//fim setGoPro

  public int getGolContra(){
   return golContra;
  }//fim getGolContra
  public void setGolContra(int golContra){
   this.golContra = golContra;
  }//fim setGolContra

  public int getSaldo(){
   return saldo;
  }//fim getSaldo
  public void setSaldo(int saldo){
   this.saldo = saldo;
  }//fim setSaldo

  public int getDoidao(){
   return doidao;
  }//fim getDoidao
  public void setDoidao(int doidao){
   this.doidao = doidao;
  }//fim setDoidao

  public void printEstatisticas(){
    MyIO.print(this.getNumPontos() + "(" + this.getPais() + ") "); 
  }
}
//--------------------------------------------------------------------------------

/**
 * classe Ranking
 */
class Ranking {
  private No raiz, j;
  private int n, posicaoLista, compare;

  /**
   * Construtor da classe.
   */
  public Ranking(){
    raiz = j = null;
    n = 0;
  }


  /**
   * Construtor da classe.
   * @param tamanho Tamanho da lista.
   */
  public void setComp(int compare){
    this.compare = compare;
  }
  public int getComp(){
    return this.compare;
  }


   /**
    * Insere um elemento na ultima posicao da lista.
    * @param x int elemento a ser inserido.
    * @throws Exception Se a lista estiver cheia.
    */
   public void inserir(Time time) throws Exception{
    raiz = inserir(time, raiz);
   } 
  
  /**
   * Insere um elemento da classe time na arvore
   * @param Time time, objeto da classe time
   */
  public No inserir(Time time, No i) throws Exception{
    if(i == null){
      i = new No(time.clone());
      n++;
    }else if(time.getPais().compareTo(i.elemento.getPais()) < 0){
      compare++;
      i.esq = inserir(time, i.esq);
    }else if(time.getPais().compareTo(i.elemento.getPais()) > 0){
      compare++;
      i.dir = inserir(time, i.dir);
    }else{
      throw new Exception("Erro ao inserir.");
    }
    return i;
  }

    public void remover(String time) throws Exception{
    raiz = remover(time, raiz);
  }

  /**
   * Remove um elemento da classe time na arvore
   * @param Time time, objeto da classe time e No i
  **/

  public No remover(String time, No i) throws Exception{
    if(i == null){
      throw new Exception("Erro ao remover.");
    }else if(time.compareTo(i.elemento.getPais()) < 0){
      compare++;
      i.esq = remover(time, i.esq);
    }else if(time.compareTo(i.elemento.getPais()) > 0){
      compare++;
      i.dir = remover(time, i.dir);
    }else if(i.dir == null){
      i = i.esq;
    }else if(i.esq == null){
      i = i.dir;
    }else{
      i.esq = anterior(i, i.esq);
    }
    n--;
    return i;
  }

  public No anterior(No i, No j){
    if(j.dir != null){
      j.dir = anterior(i, j.dir);
    }else{
      i.elemento = j.elemento;
      j = j.esq;
    }
    return j;
  }

  public void mostrarPre(){
    Time time = new Time();
    MyIO.print("[ ");
    mostrarPre(raiz);
    MyIO.print("]");
    MyIO.println("");
  }

  public void mostrarPre(No i){
    if(i != null){
      i.elemento.printEstatisticas();
      mostrarPre(i.esq);
      mostrarPre(i.dir);
    }//fim if
  }

  public void atualiza(Time time){
    j.elemento.setNumPontos(j.elemento.getNumPontos() + time.getNumPontos());
    j.elemento.setJogos(j.elemento.getJogos() + time.getJogos());
    j.elemento.setVitorias(j.elemento.getVitorias() + time.getVitorias());
    j.elemento.setEmpates(j.elemento.getEmpates() + time.getEmpates());
    j.elemento.setDerrotas(j.elemento.getDerrotas() + time.getDerrotas());
    j.elemento.setGolPro(j.elemento.getGolPro() + time.getGolPro());
    j.elemento.setGolContra(j.elemento.getGolContra() + time.getGolContra());
    j.elemento.setSaldo(j.elemento.getSaldo() + time.getSaldo());
    j.elemento.setDoidao(j.elemento.getDoidao() + time.getDoidao()); 
  }

  /**
   * Método que realiza a pesquisa para atualizar os elementos presentes nos Nós
   * @param String contendo o nome da seleção e No i que começa na raiz
   * @return boolean existePais, true se há, false se não
  **/

  public boolean pesquisar(Time time){
    return pesquisar(time, raiz);
  }

  public boolean pesquisar(Time time, No i){
    boolean existePais = false;
    if(i == null){
      existePais = false;
    }else if(time.getPais().equals(i.elemento.getPais()) == true){
      existePais = true;
      j = i;
    }else if(time.getPais().compareTo(i.elemento.getPais()) < 0){
      existePais = pesquisar(time, i.esq);
    }else if(time.getPais().compareTo(i.elemento.getPais()) > 0){
      existePais = pesquisar(time, i.dir);
    }
    return existePais;
  }

  public void processa(Time t1, Time t2){
    try{
      if (pesquisar(t1)){
        atualiza(t1);
      }
      else{
        inserir(t1);
      }

      if (pesquisar(t2)){
        atualiza(t2);
      }
      else{
        inserir(t2);
      }
    }catch(Exception E){

    }  
  }

  /**
   * Método que realiza a pesquisa do elemento na árvore e printa o caminho e SIM ou NÃO
   * @param String time e No i
   * @return boolean existePais, true se existe país, false se não existe
  **/
  public boolean pesquisarArvore(String time){
    return pesquisarArvore(time, raiz);
  }

  public boolean pesquisarArvore(String time, No i){
    boolean existePais = false;
    if(i == null){
      compare++;
      existePais = false;
    }else if(time.equals(i.elemento.getPais()) == true){
      compare++;
      existePais = true;
    }else if(time.compareTo(i.elemento.getPais()) < 0){
      compare++;
      MyIO.print(" esq");
      existePais = pesquisarArvore(time, i.esq);
    }else if(time.compareTo(i.elemento.getPais()) > 0){
      compare++;
      MyIO.print(" dir");
      existePais = pesquisarArvore(time, i.dir);
    }
    return existePais;
  }
}//fim Ranking

class No{
  public Time elemento;
  public No esq, dir;

  public No(Time elemento){
    this(elemento, null, null);
  }

  public No(Time elemento, No esq, No dir){
    this.elemento = elemento;
    this.esq = this.dir = null;
  }
}


//-----------------------------------------------------------------------------------------
public class ArvoreBinaria{

  public static void main(String[] args) throws Exception {
    Ranking ranking = new Ranking();
    Partida partida = new Partida();
    long inicio = System.currentTimeMillis();
    String html = "";
    String str = MyIO.readLine();



    while(Integer.parseInt(str) != 0){
        partida.lerHTML(Integer.parseInt(str),ranking);
        str = MyIO.readLine();
    }

    ranking.mostrarPre();
    
    try{
      ranking.remover("África do Sul");
      ranking.remover("Alemanha");
      ranking.remover("Argentina");
      ranking.remover("Brasil");
      ranking.remover("Chile");
      ranking.remover("Coreia do Sul");
      ranking.remover("Espanha");
      ranking.remover("Estados Unidos");
      ranking.remover("França");
      ranking.remover("Inglaterra");
      ranking.remover("Itália");
      ranking.remover("Japão");
      ranking.remover("México");
      ranking.remover("Suécia");
      ranking.remover("Suíça");
      ranking.remover("Uruguai");
    }catch(Exception E){

    }
    ranking.mostrarPre();

    int numPesquisa = 0;
    String[] nomePesquisa = new String[1000];
    
    do{
      nomePesquisa[numPesquisa] = MyIO.readLine();
    }while(nomePesquisa[numPesquisa++].equals("FIM") == false);
    
    numPesquisa--;
    long comeco = System.currentTimeMillis();//pega o tempo onde inicia a pesquisa
    
    for(int i = 0; i < numPesquisa; i++){
      MyIO.print(nomePesquisa[i]);
      MyIO.print(" raiz");
      boolean existe = ranking.pesquisarArvore(nomePesquisa[i]);
      if(existe){
        MyIO.print(" SIM");
        MyIO.println("");
      }else{
        MyIO.print(" NÃO");
        MyIO.println("");
      }
    }

    long fim = System.currentTimeMillis();
    long tempo = fim - inicio;
    Arq.openWrite("561288_quickSort.txt");
    Arq.print("561288\t" + tempo + "\t" + ranking.getComp());
    Arq.close();

  }
}
