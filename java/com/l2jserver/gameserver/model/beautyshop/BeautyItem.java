/*
 * Copyright (C) 2004-2015 L2J Server
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
package com.l2jserver.gameserver.model.beautyshop;

import java.util.HashMap;
import java.util.Map;

import com.l2jserver.gameserver.model.StatsSet;

/**
 * @author Sdw
 */
public class BeautyItem
{
	private final int _id;
	private final int _adena;
	private final int _resetAdena;
	private final int _beautyShopTicket;
	private final Map<Integer, BeautyItem> _colors = new HashMap<>();
	
	public BeautyItem(StatsSet set)
	{
		_id = set.getInt("id");
		_adena = set.getInt("adena", 0);
		_resetAdena = set.getInt("reset_adena", 0);
		_beautyShopTicket = set.getInt("beauty_shop_ticket", 0);
	}
	
	public int getId()
	{
		return _id;
	}
	
	public int getAdena()
	{
		return _adena;
	}
	
	public int getResetAdena()
	{
		return _resetAdena;
	}
	
	public int getBeautyShopTicket()
	{
		return _beautyShopTicket;
	}
	
	public void addColor(StatsSet set)
	{
		BeautyItem color = new BeautyItem(set);
		_colors.put(set.getInt("id"), color);
	}
	
	public Map<Integer, BeautyItem> getColors()
	{
		return _colors;
	}
}
