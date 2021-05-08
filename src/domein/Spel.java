package domein;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

import i18n.UITextHelper;

public class Spel {
	Scanner sc = new Scanner(System.in);
	private final List<Speler> spelers;
	private final Pot pot;
	private Speler spelerAanZet;
	private Tafel vasteTafel;
	private Tafel tijdelijkeTafel;
	private List<RummiSteen> werkveld;
	private DomeinController domeinController;

	/** Initialiseert alle benodigdheden voor een spel.
	 * @param spelers een lijst van de spelers die meedoen aan het spel
	 */
	public Spel(List<Speler> spelers, DomeinController domeinController) {
		this.domeinController = domeinController;
		this.pot = new Pot();
		this.vasteTafel = new Tafel();
		this.tijdelijkeTafel = new Tafel();
		this.spelers = spelers;
		this.werkveld = new ArrayList<>();
		Collections.shuffle(spelers);
		geefEerste14Stenen();
		setSpelerAanZet(spelers.get(0));
		resetTijdelijkeTafel();
		
	}
	
	/** Haalt lijst van aangemelde spelers op
	 * @return lijst van spelers.
	 */
	public List<Speler> getSpelers() {
		return spelers;
	}
	
	/** Haalt een werkveld op waar stenen vanuit de tafel tijdelijk van tafel kunnen genomen worden en in geplaatst worden.
	 * @return geeft een leeg werkveld terug
	 */
	public List<RummiSteen> getWerkveld(){
		return this.werkveld;
	}
	
	/** Haalt de pot op
	 * @return pot voor in het huidig spel.
	 */
	public Pot getPot() {
		return pot;
	}
	
	/** Haalt speler die aan de beurt is.
	 * @return Speler die aan de beurt is.
	 */
	public Speler getSpelerAanZet() {
		return spelerAanZet;
	}
	
	/** Haalt een kopie op van de vaste tafel
	 * @return de kopie
	 */
	public Tafel getTijdelijkeTafel() {
		return tijdelijkeTafel;
	}
	
	/** Stelt in wat er in de tijdelijke tafel staat
	 * @param tijdelijkeTafel de inhoud van de tijdelijke tafel */
	public void setTijdelijkeTafel(Tafel tijdelijkeTafel) {
		this.tijdelijkeTafel = tijdelijkeTafel;
	}
	
	/** Stelt een vaste speeltafel in voor het gehele spel
	 * @return de speeltafel
	 */
	public Tafel getVasteTafel() {
		return vasteTafel;
	}
	
	/** Voegt de veranderingen van de tijdelijke tafel door naar de vaste tafel
	 * @param tijdelijkeTafel de aanpassingen die aan de vaste tafel moeten gedaan worden
	 */ 
	public void setVasteTafel(Tafel tijdelijkeTafel) {
		this.vasteTafel = tijdelijkeTafel;
	}
	
	/** Verwijdert alle stenen gelegd door de speler op de tijdelijke tafel
	 */
	public void resetTijdelijkeTafel() {
		//this.tijdelijkeTafel = SerializationUtils.clone(this.vasteTafel)
		this.tijdelijkeTafel.reset(this.vasteTafel.getStenenOpTafel(), this.spelerAanZet);
		
	}
	/** Haalt voor elke speler 14 stenen uit de pot
	 */
	public void geefEerste14Stenen() {
		for (Speler speler : spelers) {
			for (int i=0;i<14;i++) {
				speler.krijgtSteen(steenUitPotHalen());
			}
			speler.kopieInstellen();
		}
		
	}
	
	/** Verwijdert een steen uit de pot.
	 * @return aanroeping van methode om eerste steen uit de pot te verwijderen en die aan de speler te geven.
	 */
	private RummiSteen steenUitPotHalen() {
		return pot.verwijderSteen();
	}
	
	/** Stelt een bepaalde speler aan zet
	 * @param spelerAanZet gekozen speler aan wie het de beurt is.
	 */
	private void setSpelerAanZet(Speler spelerAanZet) {
		this.spelerAanZet = spelerAanZet;
	}
	
	/** Toont de stenen van de speler aan zet.
	 * @return roept methode aan om stenen van een speler te geven.
	 */
	public String toonStenenSpeler() {
		return spelerAanZet.toonStenen();
	}
	
	/** Toont het werkveld als een string
	 * @return het werkveld
	 */
	public String toonWerkveld() {
		String returnString = "";
		for (RummiSteen steen: this.werkveld) {
			returnString +=  String.format("%s ",steen.toString());
		}
		return returnString;
	
	}
	
	
	/** 
	 * Berekent de scores van alle spelers
	 */
	public List<Speler> berekenScore(String gebruikersnaamWinaar) {
		int winscore = 0;
		List<Speler> spelersListScore = new ArrayList<>();
		for (Speler speler : spelers) {
			int score = 0;
			for (RummiSteen steen : speler.getStenenInBezit()) {
				score -= steen.getWaarde();
				winscore += steen.getWaarde();
			}
			if(speler.getGebruikersnaam() != gebruikersnaamWinaar) {
				domeinController.getSpelerRepo().updateScore(score, speler.getID());
				spelersListScore.add(new Speler(speler.getGebruikersnaam(), score));
			}
		}
		domeinController.getSpelerRepo().updateScore(winscore, this.getSpelerID(gebruikersnaamWinaar));
		spelersListScore.add(new Speler(gebruikersnaamWinaar, winscore));
		return spelersListScore;
	}
	
	private int getSpelerID(String gebruikersnaamWinaar) {
		for (Speler speler : spelers) {
			if(speler.getGebruikersnaam() == gebruikersnaamWinaar) {
				return speler.getID();
			}
		}
		return -1;
	}

	/** 
	 * Kijkt of de speler aan zet gewonnen is of niet
	 * */
	public boolean checkWinst() {
			return (spelerAanZet.getStenenInBezit().isEmpty()&& this.controleerTafel());
		
	}

	/** Toont de stenen op tafel
	 * @param tafel parameter die beslist of vaste tafel of tijdelijke tafel word getoond
	 * @return de stenen op de tijdelijke tafel of vaste tafel.
	 */
	public String toonStenenTafel(int tafel) {
		if (tafel<10) {
			return vasteTafel.toonStenen();
		}
		else
			return tijdelijkeTafel.toonStenen();
	}
	
	/** Stelt in of de speler een steen moet nemen of niet
	 * @param b true of false of de speler een steen moet nemen of niet
	 */ 
	public void zetNeemSteen(boolean b) {
		spelerAanZet.setNeemSteen(b);
	}
	
	/**
	 * Beëindigt beurt van speler die momenteel aan de beurt is
	 */ 
	public boolean beeindigBeurt() {
		if (controleerTafel()) {
			if (spelerAanZet.getNeemSteen())
				spelerAanZet.krijgtSteen(steenUitPotHalen());
			bepaalSpelerAanZet();
			this.zetNeemSteen(true);
			this.vasteTafel.reset(this.tijdelijkeTafel.getStenenOpTafel(), this.spelerAanZet);
			this.kopieSpelerStenenInstellen();
			return true;
		}
		else {
			return false;
		}
	}
	
	/** Controleert of de tijdelijke tafel klopt 
	 *  @return roept methode aan om de tafel te controleren
	 */
	private boolean controleerTafel() {
		return /*true;*/tijdelijkeTafel.controleerTafel();
	}
	
	/** 
	 * Bepaalt welke speler aan beurt is
	 */
	public void bepaalSpelerAanZet() {
		setSpelerAanZet(spelers.get((spelers.indexOf(spelerAanZet)+1)%spelers.size()));
	}
	
	/** 
	 * Legt een gekozen steen op tafel
	 * @param steen gekozen steen
	 * @param rij waar de speler de steen wil leggen op tafel
	 */
	public void steenOpTafelLeggen(RummiSteen steen, int rij, int kolom) {
		this.tijdelijkeTafel.legSteenOpTafel(steen, rij, kolom);
	}
	
	
	
	/** Geeft een steen terug met een bepaalde naam uit het werkveld
	 * @param naam de kleur en waarde van de steen
	 * @return de naam van de steen indien die in het werkveld ligt
	 */
	public RummiSteen geefSteenMetNaam(String naam) {
		for(RummiSteen s : this.werkveld) {
			if(s.getNaam().equals(naam)) return s;
		}
		return null;
	}
	
	
	/** Legt een steen aan op een specifieke plaats
	 * @param naam kleur en waarde van de steen
	 * @param positie rij en kolom waar steen moet komen
	 * @return wanneer je deze steen niet hebt 
	 */
	public String steenAanleggen(String naam, String positie) {
		String[] str = positie.split(",");
		int rij=0;
		int kolom=0;
		for(int i = 0; i <this.tijdelijkeTafel.getStenenOpTafel().size()+this.tijdelijkeTafel.getStenenOpTafel().get(1).size() ; i++) {
			String s = "";
			s+=i;
			if (str[0].equals(s)) {
				rij = i;
			}
			if (str[1].equals(s)) {
				kolom = i;
			}
		}
		
		
		boolean vanWerkveld = true;
		RummiSteen steen = this.geefSteenMetNaam(naam);
		if(steen==null) {
			steen = this.spelerAanZet.geefSteenMetNaam(naam);
			vanWerkveld = false;
		}
		
		if(steen != null) {
			if(vanWerkveld) {
				this.werkveld.remove(steen);
				this.steenOpTafelLeggen(steen, rij, kolom);
			}else {
				this.spelerAanZet.verwijderSteen(steen);
				this.steenOpTafelLeggen(steen, rij, kolom);
				this.zetNeemSteen(false);
			}
		}else {
			return UITextHelper.UIText("Deze.steen.heb.je.niet.in.je.bezit");
		}
		return null;
	}
	

	
	/** Neemt een steen van de tafel en verplaatst deze naar het werkveld
	 * @param naam kleur en waarde van de te verplaatsen steen
	 */
	public String steenNaarWerkveld(int Xindex, int Yindex) {	
		RummiSteen steen = this.tijdelijkeTafel.verwijderSteen(Xindex, Yindex);
		if(steen != null) {
			this.werkveld.add(steen);
		} else return UITextHelper.UIText("Deze.steen.ligt.niet.op.tafel");
		return null;
	}
	
	public String steenNaarWerkveld(RummiSteen steen) {	
		if(steen != null) {
			this.tijdelijkeTafel.verwijderSteen(steen);
			this.werkveld.add(steen);
		} else return UITextHelper.UIText("Deze.steen.ligt.niet.op.tafel");
		return null;
	}
	
	/** 
	 * Vervangt de joker door een door de speler gekozen steen
	 * @param naam
	 * @param waarde 
	 */
	public void jokerVervangen(int waarde, String naam) {
		RummiSteen joker = null;
		int Xindex = 0;
		int Yindex = 0;
		int XindexJoker = 0;
		int YindexJoker = 0;
		for (List<RummiSteen> steengroep : this.tijdelijkeTafel.getStenenOpTafel()) {
			for (RummiSteen rummisteen : steengroep) {
				if(rummisteen.getWaarde() == waarde) {
					joker = rummisteen;
					XindexJoker = Xindex;
					YindexJoker = Yindex;
				}
				Xindex++;
			}
			Xindex=0;
			Yindex++;
		}
		boolean vanWerkveld = true;
		RummiSteen steen = this.geefSteenMetNaam(naam);
		if(steen==null) {
			steen = this.spelerAanZet.geefSteenMetNaam(naam);
			vanWerkveld = false;
		}
		if(steen != null) {
			if(vanWerkveld) {
				this.werkveld.remove(steen);
			}else {
				this.spelerAanZet.verwijderSteen(steen);
				this.zetNeemSteen(false);
			}
			this.tijdelijkeTafel.getStenenOpTafel().get(YindexJoker).add(XindexJoker, steen);
			this.tijdelijkeTafel.getStenenOpTafel().get(YindexJoker).remove(joker);
			this.werkveld.add(joker);
			
		}
	}
	
	/**
	 * Methode om rij te splitsen
	 * @param yindex 
	 * @param xindex 
	 */
	public void rijSplitsen(String xindex, String yindex){
		int Xindex = VanStringEenIntMaken(xindex);
		Xindex++;
		int Yindex = VanStringEenIntMaken(yindex);
		if(Xindex <0 || Yindex < 0) {
			throw new IllegalArgumentException(UITextHelper.UIText("String.was.geen.getal"));
		}else {
			
			boolean tweeVrijAchter = false;
			boolean tweeVrijVoor = false;
			boolean drieLegePlaatsenVoor = false;
			boolean drieLegePlaatsenNa = false;
			
			int vrijePlaatsen[] = new int[this.tijdelijkeTafel.getStenenOpTafel().get(Yindex).size()];
			for(int i = 0; i < this.tijdelijkeTafel.getStenenOpTafel().get(Yindex).size() ; i++) {
				if (this.tijdelijkeTafel.getStenenOpTafel().get(Yindex).get(i).getWaarde() == 0) {
					vrijePlaatsen[i] = 1;
				}else {
					vrijePlaatsen[i] = 0;
				}
			}
			int index = 0;
			if(vrijePlaatsen[11] == 1 && vrijePlaatsen[12]==1)
				tweeVrijAchter= true;
			else if(vrijePlaatsen[0] == 1 && vrijePlaatsen[1]==1)
				tweeVrijVoor=true;
			else {
				int aantal = 0;
				
				int	a=0;
				for(int i : vrijePlaatsen) {
					if(a < Xindex) {
						
						if(i == 1)
							aantal++;
						else 
							aantal =0;
						if(aantal == 3) {
							index = a;
							drieLegePlaatsenVoor=true;
						}
							
					}else {
						if(i == 1)
							aantal++;
						else 
							aantal =0;
						if(aantal == 3) {
							index = a;
							drieLegePlaatsenNa=true;
						}	
					}
					a++;
				}
			}
			
			if(tweeVrijAchter)
				this.rijSplitsenTweeVrijAchter(Xindex, Yindex);
			else if(tweeVrijVoor) 
				this.rijSplitsenTweeVrijVoor(Xindex, Yindex);
			else if(drieLegePlaatsenVoor)
				this.rijSplitsenDrieLegePlaatsenVoor(Xindex, Yindex, index);
			else if(drieLegePlaatsenNa)
				this.rijSplitsenDrieLegePlaatsenNa(Xindex, Yindex, index);
			else
				this.rijSplitsenNaarWerkveld(Xindex, Yindex);
			
			
		}
	}
	
	private void rijSplitsenDrieLegePlaatsenVoor(int Xindex, int Yindex, int index) {
		Xindex-=2;
		RummiSteen steen1 = this.tijdelijkeTafel.getStenenOpTafel().get(Yindex).remove(index-1);
		RummiSteen steen2 = this.tijdelijkeTafel.getStenenOpTafel().get(Yindex).remove(index-2);
		this.tijdelijkeTafel.getStenenOpTafel().get(Yindex).add(Xindex, steen1);
		this.tijdelijkeTafel.getStenenOpTafel().get(Yindex).add(Xindex, steen2);
		
	}

	private void rijSplitsenNaarWerkveld(int Xindex, int Yindex) {
		boolean legePlaatsGehad = false;
		ArrayList<RummiSteen> naarWerkveld = new ArrayList<>();
		for(RummiSteen steen : this.tijdelijkeTafel.getStenenOpTafel().get(Yindex)) {
			if(this.tijdelijkeTafel.getStenenOpTafel().get(Yindex).indexOf(steen)>=Xindex) {
				
				if(steen.getWaarde() == 0) {
					legePlaatsGehad = true;
				}else if(!legePlaatsGehad) {
					naarWerkveld.add(steen);
				}
		
			}
		}
		naarWerkveld.forEach(steen -> this.steenNaarWerkveld(steen));
		
	}

	private void rijSplitsenDrieLegePlaatsenNa(int Xindex, int Yindex, int index) {
		RummiSteen steen1 = this.tijdelijkeTafel.getStenenOpTafel().get(Yindex).remove(index-2);
		RummiSteen steen2 = this.tijdelijkeTafel.getStenenOpTafel().get(Yindex).remove(index-1);
		this.tijdelijkeTafel.getStenenOpTafel().get(Yindex).add(Xindex, steen1);
		this.tijdelijkeTafel.getStenenOpTafel().get(Yindex).add(Xindex, steen2);
		
		
	}

	private void rijSplitsenTweeVrijVoor(int Xindex, int Yindex) {
		Xindex-=2;
		RummiSteen steen1 = this.tijdelijkeTafel.getStenenOpTafel().get(Yindex).remove(1);
		RummiSteen steen2 = this.tijdelijkeTafel.getStenenOpTafel().get(Yindex).remove(0);
		this.tijdelijkeTafel.getStenenOpTafel().get(Yindex).add(Xindex, steen1);
		this.tijdelijkeTafel.getStenenOpTafel().get(Yindex).add(Xindex, steen2);
		
	}

	private void rijSplitsenTweeVrijAchter(int Xindex, int Yindex) {
		RummiSteen steen1 = this.tijdelijkeTafel.getStenenOpTafel().get(Yindex).remove(12);
		RummiSteen steen2 = this.tijdelijkeTafel.getStenenOpTafel().get(Yindex).remove(11);
		this.tijdelijkeTafel.getStenenOpTafel().get(Yindex).add(Xindex, steen1);
		this.tijdelijkeTafel.getStenenOpTafel().get(Yindex).add(Xindex, steen2);
	}

	public int VanStringEenIntMaken(String s) {
		for(int i = 0;  i <100; i++) {
			String getal = "";
			getal+=i;
			if(s.equals(getal)) {
				return i;
			}
		}
		return -1;
	}

	public boolean heeftTafelEenJoker() {
		for(List<RummiSteen> steengroep : this.tijdelijkeTafel.getStenenOpTafel()) {
			for(RummiSteen steen : steengroep) {
				if(steen.getKleur().equals("Groen"))
					return true;
			}
		}
		return false;
	}
	

	public void kopieSpelerStenenInstellen() {
		this.spelerAanZet.kopieInstellen();
	}

	
	public void reset() {
		this.resetTijdelijkeTafel();
		this.spelerAanZet.reset();
		this.zetNeemSteen(true);
		this.werkveld.clear();
	}
}
