/* ----------------------------------------------------------------------
 * BinFile.java
 * version 3.0
 * Copyright (C) 2015  José Galaviz,
 * Faculty of Sciences, Universidad Nacional Autónoma de México, Mexico.
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, visit the following URL:
 * http://www.gnu.org/licenses/gpl.html
 * or write to the Free Software Foundation, Inc.,
 * 59 Temple Place - Suite 330, Boston, MA  02111-1307, USA.
 * ----------------------------------------------------------------------
 */
package mx.unam.fciencias.binio;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.FileNotFoundException;

/**
 * Provides access to files in binary mode. The user can
 * read/write the file bit by bit or byte by byte.
 *
 * @author José Galaviz (galaviz@ciencias.unam.mx)
 * @version 3.0 <br>
 * april 2015
 */
public class BinFile {

    /**
     * Error message: Input file not found.
     */
    private static final String ERR_FNF = "Input file not found";

    /**
     * Error message: Output file cannot be open.
     */
    private static final String ERR_FCO = "Output file cannot be open";

    /**
     * Error message: File access denied (security violation?).
     */
    private static final String ERR_IAF
                               = "File access denied (security violation?)";

    /**
     * Error message: I/O error.
     */
    private static final String ERR_IO = "I/O error";

    /**
     * Error message: Trying to read beyond EOF.
     */
    private static final String ERR_OF = "Trying to read beyond EOF";

    /**
     * Error message: File error.
     */
    private static final String ERR_GE = "File error";

    /**
     * Error message: Invalid option.
     */
    private static final String ERR_OP = "Invalid option";

    /**
     * Input file flag.
     */
    public static final int INPUT = 0;

    /**
     * Output file flag.
     */
    public static final int OUTPUT = 1;

    /**
     * Size of <code>int</code>, 4 bytes = 32 bits.
     */
    private static final int SIZEOFINT = 4;

    /**
     * Buffer size (constant = 8KB).
     */
    private static final int TAMBLOQUE = 8192;

    /**
     * The input file stream.
     */
    private FileInputStream streaml;

    /**
     * The output file stream.
     */
    private FileOutputStream streame;

    /**
     * index of next bit to be read in the next byte.
     */
    private int ibit;

    /**
     * Index of next byte to be read.
     */
    private int ibyte;

    /**
     * File size.
     */
    private int tamano;

    /**
     * Access mode INPUT/OUTPUT.
     * @see INPUT
     * @see OUTPUT
     */
    private int modo;

    /**
     * Buffer size (variable established with the TAMBLOQUE
     * value).
     */
    private int tbloque;

    /**
     * The buffer.
     */
    private byte[] buff;

    /**
     * End of file flag.
     */
    private boolean eofflag;

    /**
     * Number of bytes read or writen.
     */
    private int nbytesl;

    /**
     * Builds an instance of <code>BinFile</code>.
     */
    public BinFile() {
        ibit = 0;
        modo = 0;
        eofflag = false;
        streaml = null;
        streame = null;
        tbloque = TAMBLOQUE;
        buff = null;
        ibyte = 0;
        tamano = 0;
        nbytesl = 0;
    }

    /**
     * Open the file to be read or write.
     * @param fname file name.
     * @param mode access mode:
     * <UL>
     * <LI> <code>BinFile.INPUT</code> for reading. </LI>
     * <LI> <code>BinFile.OUTPUT</code> for writing.</LI>
     *</UL>
     * @throws BinIOException If:
     * <UL>
     * <LI> Input file not found.</LI>
     * <LI> Input file cannot be open for reading. </LI>
     * <LI> Invalid mode value.</LI>
     * </UL>
     */
    public void open(String fname, int mode) throws BinIOException {
        ibit = 0;
        modo = 0;
        eofflag = false;
        buff = null;
        streaml = null;
        streame = null;
        ibyte = 0;
        tamano = 0;
        nbytesl = 0;
        modo = mode;

        switch (modo) {
        case INPUT: // open for input

            try {
                streaml = new FileInputStream(fname);
                tamano = streaml.available();
                buff = new byte[tbloque];
                streaml.read(buff, 0, tbloque); // reads a block
            } catch (FileNotFoundException fnf) {
                throw new BinIOException(
                        this.getClass().getName() + ".open: " + ERR_FNF + " "
                        + "<" + fname + ">");
            } catch (IOException ioe) {
                throw new BinIOException(
                        this.getClass().getName() + ".open: " + ERR_IO + " "
                        + "<" + fname + ">");
            }

            break;

        case OUTPUT: // open for writing

            try {
                streame = new FileOutputStream(fname);
            } catch (SecurityException esec) {
                throw new BinIOException(
                        this.getClass().getName() + ".open: " + ERR_IAF + " "
                        + "<" + fname + ">");
            } catch (FileNotFoundException fnf) {
                throw new BinIOException(
                        this.getClass().getName() + ".open: " + ERR_FCO + " "
                        + "<" + fname + ">");
            }

            buff = new byte[tbloque]; // allocate memory

            break;

        default:
            throw new BinIOException(
                    this.getClass().getName() + ".open: " + ERR_OP);
        }
    }

    /**
     * Close the file. Flush the buffer to disk if the file
     * was open for writing.
     * @throws BinIOException if the operation cannot be
     * completed.
     */
    public void close() throws BinIOException {
        try {
            if (modo == OUTPUT) { // if output file

                if (ibit != 0) { // and the next byte was used
                    streame.write(buff, 0, ibyte + 1); // flush it
                } else { // only flush to the current byte
                    // completely used
                    streame.write(buff, 0, ibyte);
                }

                streame.close();
            } else { // input file
                streaml.close();
            }
        } catch (IOException ioe) {
            throw new BinIOException(
                    this.getClass().getName() + ".close: " + ERR_IO);
        }

        // reset the state variables
        ibit = 0;
        modo = 0;
        eofflag = false;
        ibyte = 0;
        tamano = 0;
        nbytesl = 0;
        buff = null;
        streaml = null;
        streame = null;
    }

    /**
     * Reads the next complete byte in the input file. If the
     * previous reading operation was a <code>readBit</code>
     * and a byte was not readed completely by that method,
     * the residual bits of previous byte are not accessible
     * anymore. The user must read the byte completely bit by
     * bit if data lost is not desired.
     * @return the byte that was read.
     * @throws BinIOException if you attempt to read beyond the end of
     * file or some other I/O error occurs.
     */
    public byte readByte() throws BinIOException {
        byte aux;

        // set the EOF flag for the next read
        eofflag = (nbytesl >= (tamano - 1));

        if (nbytesl < tamano) {
            if (ibyte == tbloque) { // if buffer is completely read

                try { // read next block from disk
                    streaml.read(buff, 0, tbloque);
                } catch (IOException ioe) {
                    throw new BinIOException(
                            this.getClass().getName() + ".readByte: " + ERR_IO);
                }

                ibyte = 0; // and set the byte index to zero
            }

            aux = buff[ibyte]; // the next byte
            ibyte++;
            nbytesl++;
            ibit = 0;

            return aux;
        } else {
            throw new BinIOException(
                    this.getClass().getName() + ".readByte: " + ERR_OF);
        }
    }

    /**
     * Write the next byte in the output file.
     * @param b the byte to be written
     * @throws BinIOException if the buffer is full and cannot be
     * flushed to disk.
     */
    public void writeByte(byte b) throws BinIOException {
        if (ibyte == tbloque) { // if buffer is full

            try {
                streame.write(buff, 0, tbloque); // flush the block
                ibyte = 0;
            } catch (IOException ioe) {
                throw new BinIOException(
                        this.getClass().getName() + ".writeByte: " + ERR_IO);
            }
        }

        buff[ibyte] = b;
        ibyte++;
        nbytesl++;
        ibit = 0;
    }

    /**
     * Check if the end of file is been reached. Further
     * reads will throw an exception after this method
     * returns <code>true</code>.
     * @return <code>true</code> if the end of file is reached,
     * <code>false</code> in other case.
     */
    public boolean endOfBinFile() {
        return eofflag;
    }

    /**
     * Reads the next bit in the input file.
     * @return <code>true</code> if the read bit value is
     * 1. <code>false</code> otherwise.
     * @throws BinIOException if you attempt to read beyond the end of
     * file or some other I/O error occurs.
     */
    public boolean readBit() throws BinIOException {
        byte msk1;
        byte msk2;
        byte res;
        byte aux;

        eofflag = ((nbytesl == (tamano - 1)) && (ibit == 7));

        if (nbytesl < tamano) {
            if (ibyte == tbloque) {
                try { // if buffer is completely read
                    streaml.read(buff, 0, tbloque);
                } catch (IOException ioe) {
                    throw new BinIOException(
                            this.getClass().getName() + ".readBit: " + ERR_IO);
                }

                ibyte = 0;
            }

            aux = buff[ibyte];

            // in order to filter the bit requested
            msk1 = (byte) 0X00000080;
            msk2 = (byte) (msk1 >>> ibit);
            res = (byte) ((msk2 & aux) >>> (7 - ibit));
            res &= 0X00000001;

            // increase the bit counter
            ibit = (ibit + 1) % 8;

            // and the byte counter if needed
            if (ibit == 0) {
                nbytesl++;
                ibyte++;
            }

            return (res == 1);
        } else {
            throw new BinIOException(
                    this.getClass().getName() + ".readBit: " + ERR_OF);
        }
    }

    /**
     * Write the next bit in the output file.
     * @param bitvalue <code>true</code> means 1,
     * <code>false</code> means 0.
     * @throws BinIOException in case of some I/O error.
     */
    public void writeBit(boolean bitvalue) throws BinIOException {
        byte msk1;
        byte msk2;
        byte res;
        byte aux;

        if (ibyte == tbloque) {
            try { // if buffer is full
                streame.write(buff, 0, tbloque);
                ibyte = 0;
            } catch (IOException ioe) {
                throw new BinIOException(
                        this.getClass().getName() + ".writeBit: " + ERR_IO);
            }
        }

        // aux = current value of byte where the bit is
        aux = buff[ibyte];

        // set the mask to set or clear the bit
        msk1 = (byte) 0X000000FF;
        msk1 = (byte) (msk1 << (8 - ibit));
        aux = (byte) (aux & msk1);
        msk2 = ((bitvalue)
                ? (byte) (0X00000001 << (7 - ibit))
                : (byte) (0X00000000));

        // update the byte
        res = (byte) (aux | msk2);
        buff[ibyte] = res;

        // update the counters
        ibit = (ibit + 1) % 8;

        if (ibit == 0) {
            nbytesl++;
            ibyte++;
        }
    }

    /**
     * Returns the file size. If the instance is an input
     * file returns the total number of bytes available in
     * file for reading. Otherwise returns the number of
     * bytes written.
     * @return file size in bytes.
     */
    public long getSize() {
        if (modo == INPUT) {
            return tamano;
        } else {
            return nbytesl;
        }
    }

    /**
     * Write an <code>int</code> in file, byte order (endianess) is
     * specified. Four bytes is the assumed size for an
     * integer.
     * @param num the integer to be written
     * @param little specifies the byte ordering convention
     * to be used: <i>little-endian</i> if <code>true</code>;
     * <i>big-endian</i> otherwise.
     * @throws BinIOException if the buffer is full and cannot be
     * flushed to disk.
     */
    public void writeInt(int num, boolean little) throws BinIOException {
        int i;
        byte aux;

        for (i = 0; i < SIZEOFINT; i++) {
            if (little) { // little-endian
                aux = (byte) (num >>> (i * 8));
            } else { // big-endian
                aux = (byte) (num >>> ((3 - i) * 8));
            }

            writeByte(aux);
        }
    }

    /**
     * Reads an integer (<code>int</code> type) from file,
     * the byte ordering is specified.
     * @param little specifies the byte ordering convention to be
     * used: <i>little-endian</i> if <code>true</code>,
     * <i>big-endian</i> otherwise.
     * @return the integer value read.
     * @throws BinIOException if you attempt to read beyond the end of
     * file or some other I/O error occurs.
     */
    public int readInt(boolean little) throws BinIOException {
        int i;
        int aux = 0;
        int aux2 = 0;

        for (i = 0; i < SIZEOFINT; i++) {
            if (!little) { // big-endian
                aux <<= 8;

                // aux |= (int) readByte();
                aux |= (((int) readByte()) & 0X000000FF);
            } else { // little-endian
                aux2 = 0;

                // bug fixed august 13 2001
                // before:  aux2 = (int) readByte();
                // but Java preserves the sign after the cast
                // therefore negative values can be obtained.
                // The solution: clear the most significative
                // bits.
                aux2 = ((int) readByte()) & 0X000000FF;
                aux2 <<= (8 * i);
                aux |= aux2;
            }
        }

        return aux;
    }

    /**
     * Testing program. Copy a file into another.
     * @param args input and output filenames must be given
     * in command line, the input file first.
     */
    public static void main(String[] args) {
        BinFile fent = new BinFile();
        BinFile fsal = new BinFile();
        int count;
        int bitcount;

        try {
            // testing byte read/write
            fent.open(args[0], BinFile.INPUT);
            fsal.open(args[1] + ".byte", BinFile.OUTPUT);
            System.out.println("Total: " + fent.getSize() + " bytes");
            count = 0;

            while (!fent.endOfBinFile()) {
                fsal.writeByte(fent.readByte());
                count++;
                System.out.print("bytes = " + count);
                System.out.print("");
            }

            fsal.close();
            fent.close();

            fent.open(args[0], BinFile.INPUT);
            fsal.open(args[1] + ".bit", BinFile.OUTPUT);
            System.out.println("Total: " + fent.getSize() + " bytes");
            count = 0;
            bitcount = 0;

            while (!fent.endOfBinFile()) {
                fsal.writeBit(fent.readBit());
                bitcount++;

                if (bitcount == 8) {
                    count++;
                }

                bitcount = bitcount % 8;
                System.out.print("bytes = " + count);
                System.out.print("");
            }

            fsal.close();
            fent.close();
        } catch (BinIOException ebf) {
            System.out.println(ebf.getMessage());
            ebf.printStackTrace();
        }
    }
} // BinFile.java ends here
