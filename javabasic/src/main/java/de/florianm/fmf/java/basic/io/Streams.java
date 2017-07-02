package de.florianm.fmf.java.basic.io;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.Charset;

/**
 * Utility collection of stream related operations.
 */
public final class Streams {
    /**
     * No direct instances for this class
     */
    private Streams() {
        throw new AssertionError("NO INSTANCE");
    }

    /**
     * Copy all bytes of an input stream to an output stream.
     *
     * @param in
     *     The input stream, where to copy from.
     * @param out
     *     The output stream, where to copy to.
     *
     * @return The number of bytes copied from the input to the output stream.
     *
     * @throws IOException
     *     Thrown if there was an error reading from the input stream or writing to the output stream.
     */
    public static int copy(InputStream in, OutputStream out) throws IOException {
        assert null != in;
        assert null != out;

        byte[] buffer = new byte[1024];
        int len;
        int copiedBytes = 0;

        while (-1 != (len = in.read(buffer))) {
            out.write(buffer, 0, len);
            copiedBytes += len;
        }

        return copiedBytes;
    }

    /**
     * Read all bytes of an input stream.
     *
     * @param in
     *     The input stream to read from.
     *
     * @return An array of all read bytes.
     *
     * @throws IOException
     *     Thrown if there was an error reading the data of the input stream.
     */
    public static byte[] readBytes(InputStream in) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        copy(in, baos);
        return baos.toByteArray();
    }

    /**
     * Write a byte array to an output stream.
     *
     * @param data
     *     The data to write. The maximum number of bytes to read from the data.
     * @param out
     *     The output stream to write the data to.
     *
     * @return The number of bytes written to the output stream.
     *
     * @throws IOException
     *     Thrown if there was a problem writing the bytes to the output stream.
     */
    public static int writeBytes(byte[] data, OutputStream out) throws IOException {
        ByteArrayInputStream bais = new ByteArrayInputStream(data);
        return copy(bais, out);
    }

    /**
     * Write a slice of a byte array to an output stream.
     *
     * @param data
     *     The data to write.
     * @param offset
     *     The offset in the data of the first byte to read.
     * @param length
     *     The maximum number of bytes to read from the data.
     * @param out
     *     The output stream to write the data to.
     *
     * @return The number of bytes written to the output stream.
     *
     * @throws IOException
     *     Thrown if there was a problem writing the bytes to the output stream.
     */
    public static int writeBytes(byte[] data, int offset, int length, OutputStream out) throws IOException {
        ByteArrayInputStream bais = new ByteArrayInputStream(data, offset, length);
        return copy(bais, out);
    }

    /**
     * Reads all bytes of a input stream and copies them to a string with using the default charset..
     *
     * @param in
     *     The input stream to read from.
     *
     * @return The read string.
     *
     * @throws IOException
     *     Thrown if there was an error reading from the input stream or writing the data to the string.
     */
    public static String readString(InputStream in) throws IOException {
        byte[] data = readBytes(in);
        return new String(data, Charset.defaultCharset());
    }

    /**
     * Reads all bytes of a input stream and copies them to a string with a given charset name.
     *
     * @param in
     *     The input stream to read from.
     * @param charsetName
     *     The charset name to use for the string.
     *
     * @return The read string.
     *
     * @throws IOException
     *     Thrown if there was an error reading from the input stream or writing the data to the string.
     */
    public static String readString(InputStream in, String charsetName) throws IOException {
        byte[] data = readBytes(in);
        return new String(data, Charset.forName(charsetName));
    }

    /**
     * Reads all bytes of a input stream and copies them to a string with a given charset.
     *
     * @param in
     *     The input stream to read from.
     * @param charset
     *     The charset to use for the string.
     *
     * @return The read string.
     *
     * @throws IOException
     *     Thrown if there was an error reading from the input stream or writing the data to the string.
     */
    public static String readString(InputStream in, Charset charset) throws IOException {
        byte[] data = readBytes(in);
        return new String(data, charset);
    }

    /**
     * Write a string to an output stream.
     *
     * @param value
     *     The string to write.
     * @param out
     *     The output stream to write to.
     *
     * @return The number of written bytes to the output stream.
     *
     * @throws IOException
     *     Thrown if there was an error writing the string to the output stream.
     */
    public int writeString(String value, OutputStream out) throws IOException {
        byte[] data = value.getBytes();
        return writeBytes(data, out);
    }

    /**
     * Write a string to an output stream using the given charset name.
     *
     * @param value
     *     The string to write.
     * @param charsetName
     *     The charset name to use.
     * @param out
     *     The output stream to write to.
     *
     * @return The number of written bytes to the output stream.
     *
     * @throws IOException
     *     Thrown if there was an error writing the string to the output stream.
     */
    public int writeString(String value, String charsetName, OutputStream out) throws IOException {
        byte[] data = value.getBytes(charsetName);
        return writeBytes(data, out);
    }

    /**
     * Write a string to an output stream using the given charset.
     *
     * @param value
     *     The string to write.
     * @param charset
     *     The charset to use.
     * @param out
     *     The output stream to write to.
     *
     * @return The number of written bytes to the output stream.
     *
     * @throws IOException
     *     Thrown if there was an error writing the string to the output stream.
     */
    public int writeString(String value, Charset charset, OutputStream out) throws IOException {
        byte[] data = value.getBytes(charset);
        return writeBytes(data, out);
    }
}
