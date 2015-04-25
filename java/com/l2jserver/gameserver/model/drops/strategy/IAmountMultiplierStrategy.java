/*
 * Copyright (C) 2004-2014 L2J Server
 * 
 * This file is part of L2J Server.
 * 
 * L2J Server is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * L2J Server is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package com.l2jserver.gameserver.model.drops.strategy;

import com.l2jserver.Config;
import com.l2jserver.gameserver.datatables.ItemTable;
import com.l2jserver.gameserver.model.actor.L2Character;
import com.l2jserver.gameserver.model.drops.GeneralDropItem;

/**
 * @author Battlecruiser
 */
public interface IAmountMultiplierStrategy
{
	public static final IAmountMultiplierStrategy DROP = DEFAULT_STRATEGY(Config.RATE_DEATH_DROP_AMOUNT_MULTIPLIER);
	public static final IAmountMultiplierStrategy SPOIL = DEFAULT_STRATEGY(Config.RATE_CORPSE_DROP_AMOUNT_MULTIPLIER);
	public static final IAmountMultiplierStrategy STATIC = (item, victim) -> 1;
	
	public static IAmountMultiplierStrategy DEFAULT_STRATEGY(final double defaultMultiplier)
	{
		return (item, victim) ->
		{
			double multiplier = 1;
			
			Float dropChanceMultiplier = Config.RATE_DROP_AMOUNT_MULTIPLIER.get(item.getItemId());
			if (dropChanceMultiplier != null)
			{
				multiplier *= dropChanceMultiplier;
			}
			else if (ItemTable.getInstance().getTemplate(item.getItemId()).hasExImmediateEffect())
			{
				multiplier *= Config.RATE_HERB_DROP_AMOUNT_MULTIPLIER;
			}
			else if (victim.isRaid())
			{
				multiplier *= Config.RATE_RAID_DROP_AMOUNT_MULTIPLIER;
			}
			else
			{
				multiplier *= defaultMultiplier;
			}
			return multiplier;
		};
	}
	
	public double getAmountMultiplier(GeneralDropItem item, L2Character victim);
}