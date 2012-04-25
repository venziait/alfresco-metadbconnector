/*
 *    Copyright (C) 2012 Venzia, S.L. All rights reserved.
 *
 *    This program is free software: you can redistribute it and/or modify
 *    it under the terms of the GNU General Public License as published by
 *    the Free Software Foundation, either version 3 of the License, or
 *    (at your option) any later version.
 *
 *    This program is distributed in the hope that it will be useful,
 *    but WITHOUT ANY WARRANTY; without even the implied warranty of
 *    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 *    GNU General Public License for more details.
 *
 *    You should have received a copy of the GNU General Public License
 *    along with this program. If not, see <http://www.gnu.org/licenses/>.
 *    
 */

/**
 * @author horelvis castillo mendoza
 *
 */

package org.venzia.mdbc;

import org.alfresco.repo.module.AbstractModuleComponent;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * A basic component that will be started for this module.
 * 
 * @author Derek Hulley
 */
public class MDBCComponent extends AbstractModuleComponent
{
	Log log = LogFactory.getLog(MDBCComponent.class);
	
    @Override
    protected void executeInternal() throws Throwable
    {
        System.out.println("MDBCComponent has been executed");
        //TODO: METER AQUI EL CODIGO DEL MODULO MDBCConnectorSystem
        log.info(this.toString());
    }
}
