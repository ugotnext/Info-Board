
package com.ktar5.infoboard.Scroll;

import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;

import com.ktar5.infoboard.InfoBoard;
import com.ktar5.infoboard.Scoreboard.Board;
import com.ktar5.infoboard.Util.Settings;


public class ScrollText {
	
	public static void scroll(Player player) {
		// Make sure the user can see the board
		if (!Settings.isWorldDisabled(player.getWorld().getName()) && !InfoBoard.hidefrom.contains(player.getName()) && ((player.getScoreboard().getObjective(DisplaySlot.SIDEBAR) == null) || player.getScoreboard().getObjective(DisplaySlot.SIDEBAR).getName().equalsIgnoreCase("InfoBoard")))
		{
			if (ScrollManager.getScrollers(player) != null)
				for (Scroll sc : ScrollManager.getScrollers(player))
					try
					{
						// Move scroller over one, and add the new line
						sc.next();
						String newLine = sc.getMessage();
						
						Board board = new Board(player);
						
						board.update(newLine, sc.getRow());
					}
					catch (Exception e)
					{
					}
			
			if (ScrollManager.getTitleScroller(player) != null)
				try
				{
					Scroll sc = ScrollManager.getTitleScroller(player);
					
					// Change the boards title to the next scroller
					sc.next();
					String newLine = sc.getMessage();
					
					Board board = new Board(player);
					
					board.setTitle(newLine);
				}
				catch (Exception e)
				{
				}
			
		}
		else
			ScrollManager.reset(player);
		
	}
}
