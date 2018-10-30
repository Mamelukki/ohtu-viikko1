/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ohtuesimerkki;

import java.util.ArrayList;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class StatisticsTest {
 
    Reader readerStub = new Reader() {
 
        public List<Player> getPlayers() {
            ArrayList<Player> players = new ArrayList<Player>();
 
            players.add(new Player("Semenko", "EDM", 4, 12));
            players.add(new Player("Lemieux", "PIT", 45, 54));
            players.add(new Player("Kurri",   "EDM", 37, 53));
            players.add(new Player("Yzerman", "DET", 42, 56));
            players.add(new Player("Gretzky", "EDM", 35, 89));
 
            return players;
        }
    };
 
    Statistics stats;

    @Before
    public void setUp(){
        // luodaan Statistics-olio joka käyttää "stubia"
        stats = new Statistics(readerStub);
    }  
    
    @Test
    public void searchReturnsNullWhenNoPlayerIsFound() {
        Player player = stats.search("abc");
        assertEquals(null, stats.search("abc"));
    }
    
    @Test
    public void searchFindsPlayerByFullName() {
        Player player = stats.search("Kurri");
        assertEquals("Kurri", player.getName());
    }
    
    @Test
    public void searchFindsPlayerByPartialName() {
        Player player = stats.search("menko");
        assertEquals("Semenko", player.getName());
    }
    
    @Test
    public void teamFindsSinglePlayer() {
        List<Player> team = stats.team("DET");
        
        assertEquals(1, team.size());
    }
    
    @Test
    public void teamFindsMultiplePlayers() {
        List<Player> team = stats.team("EDM");
        
        assertEquals(3, team.size());
    }
    
    @Test
    public void topScorersReturnsRightPlayers() {
        List<Player> top = stats.topScorers(2);

        assertEquals("Gretzky", top.get(0).getName());
        assertEquals("Lemieux", top.get(1).getName());
    }
    
}
