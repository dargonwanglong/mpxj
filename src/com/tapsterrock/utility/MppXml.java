/*
 * file:       MppXml.java
 * author:     Jon Iles
 * copyright:  (c) Tapster Rock Limited 2002-2003
 * date:       21/10/2003
 */

/*
 * This library is free software; you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as published by the
 * Free Software Foundation; either version 2.1 of the License, or (at your
 * option) any later version.
 *
 * This library is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY
 * or FITNESS FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public
 * License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this library; if not, write to the Free Software Foundation, Inc.,
 * 59 Temple Place, Suite 330, Boston, MA 02111-1307, USA.
 */

package com.tapsterrock.utility;

import com.tapsterrock.mpp.MPPFile;
import com.tapsterrock.mspdi.MSPDIFile;

/**
 * This is a trivial utility class used to provide a simple file
 * format conversion tool that can be run from the command line.
 * It takes the name of an input MPX file file, and the name of an MPSPDI file
 * to which the contents of the MPX file will be written.
 */
public class MppXml
{
   /**
    * Main method.
    *
    * @param args array of command line arguments
    */
   public static void main (String[] args)
   {
      try
      {
         if (args.length != 2)
         {
            System.out.println ("Usage: MppXml <input mpp file name> <output xml file name>");
         }
         else
         {
            MPPFile mpx = new MPPFile (args[0]);
            MSPDIFile xml = new MSPDIFile (mpx);
            xml.write(args[1]);
         }
      }

      catch (Exception ex)
      {
         ex.printStackTrace(System.out);
      }
   }
}
