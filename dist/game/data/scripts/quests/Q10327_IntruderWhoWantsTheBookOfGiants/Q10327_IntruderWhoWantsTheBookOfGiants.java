/*
 * Copyright (C) 2004-2015 L2J DataPack
 * 
 * This file is part of L2J DataPack.
 * 
 * L2J DataPack is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * L2J DataPack is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package quests.Q10327_IntruderWhoWantsTheBookOfGiants;

import quests.Q10326_RespectYourElders.Q10326_RespectYourElders;

import com.l2jserver.gameserver.model.actor.L2Npc;
import com.l2jserver.gameserver.model.actor.instance.L2PcInstance;
import com.l2jserver.gameserver.model.holders.ItemHolder;
import com.l2jserver.gameserver.model.quest.Quest;
import com.l2jserver.gameserver.model.quest.QuestState;
import com.l2jserver.gameserver.model.quest.State;
import com.l2jserver.gameserver.network.NpcStringId;
import com.l2jserver.gameserver.network.serverpackets.ExShowScreenMessage;

/**
 * @author Neanrakyr
 */
public class Q10327_IntruderWhoWantsTheBookOfGiants extends Quest
{
	// Npcs
	private static final int PANTHEON = 32972;
	// Items
	private static final int THE_WAR_OF_GODS_AND_GIANTS = 17575;
	private static final ItemHolder APPRENTICE_EARRING = new ItemHolder(112, 2);
	// Level Condition
	private static final int MAX_LEVEL = 20;
	
	public Q10327_IntruderWhoWantsTheBookOfGiants()
	{
		super(10327, Q10327_IntruderWhoWantsTheBookOfGiants.class.getSimpleName(), "Intruder Who Wants the Book of Giants ");
		addStartNpc(PANTHEON);
		addTalkId(PANTHEON);
		registerQuestItems(THE_WAR_OF_GODS_AND_GIANTS);
		addCondMaxLevel(MAX_LEVEL, "32972-09.htm");
		addCondCompletedQuest(Q10326_RespectYourElders.class.getSimpleName(), "32972-09.htm");
	}
	
	@Override
	public String onAdvEvent(String event, L2Npc npc, L2PcInstance player)
	{
		final QuestState qs = getQuestState(player, false);
		if (qs == null)
		{
			return null;
		}
		
		String htmltext = null;
		switch (event)
		{
			case "32972-02.htm":
			{
				htmltext = event;
				break;
			}
			case "32972-03.htm":
			{
				qs.startQuest();
				htmltext = event;
				break;
			}
			case "32972-07.htm":
			{
				if (qs.isCond(3))
				{
					showOnScreenMsg(player, NpcStringId.ACCESSORIES_HAVE_BEEN_ADDED_TO_YOUR_INVENTORY, ExShowScreenMessage.TOP_CENTER, 4500);
					giveAdena(player, 160, true);
					giveItems(player, APPRENTICE_EARRING);
					addExpAndSp(player, 7800, 5);
					qs.exitQuest(false, true);
					break;
				}
				break;
			}
		}
		return htmltext;
	}
	
	@Override
	public String onTalk(L2Npc npc, L2PcInstance player)
	{
		final QuestState qs = getQuestState(player, true);
		String htmltext = null;
		
		switch (qs.getState())
		{
			case State.CREATED:
			{
				htmltext = "32972-01.htm";
				break;
			}
			case State.STARTED:
			{
				if (qs.isCond(1))
				{
					htmltext = "32972-04.htm";
					break;
				}
				else if (qs.isCond(2))
				{
					htmltext = "32972-05.htm";
					break;
				}
				else if (qs.isCond(3))
				{
					htmltext = "32972-06.htm";
					break;
				}
			}
			case State.COMPLETED:
			{
				htmltext = "32972-08.htm";
				break;
			}
		}
		return htmltext;
	}
}