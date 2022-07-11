package com.edimca.core.service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.edimca.core.model.Board;
import com.edimca.core.model.Cut;
import com.edimca.core.model.Job;
import com.edimca.core.model.OrderBiesse;
import com.edimca.core.model.OrderLepton;
import com.edimca.core.model.Part;
import com.edimca.core.model.PartBiesse;
import com.edimca.core.model.Piece;
import com.edimca.core.model.Program;
import com.edimca.core.model.ValidatorModel;
import com.edimca.core.model.WorkList;
import com.edimca.core.model.WorkListBiesse;

import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Attr;

@Service
public class FileServiceImpl implements FileServiceAPI {

	private String UPLOADS_FILE_LOCATION = null;
	private Path UPLOADS_FILE_PATH = null;
	private String ACCOUNT_STATE_FILE_LOCATION = null;
	private Path ACCOUNT_STATE_FILE_PATH = null;
	private String DOWNLOADS_FILE_LOCATION = null;
	private Path DOWNLOADS_FILE_PATH = null;

	public static String separator = System.getProperty("file.separator");

	@Override
	public ValidatorModel storeFile(MultipartFile file) {
		ValidatorModel valid = new ValidatorModel("storeFile");
		String fileName = null;
		try {
			initFilePaths();
			fileName = StringUtils.cleanPath(file.getOriginalFilename());
			Path fileStorageLocation = Paths.get(ACCOUNT_STATE_FILE_LOCATION);
			Path filePath = fileStorageLocation.resolve(fileName).normalize();
			Resource resource = new UrlResource(fileStorageLocation.toUri());

			if (!resource.exists()) {
				valid.setLog("Directorio no encontrado");
			} else {
				// Copy file to the target location (Replacing existing file with the same name)
				Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

				valid.setObj(mappingLeptonXml(filePath.toString(), fileName));
				valid.setSuccess(true);
			}

			return valid;
		} catch (Exception ex) {
			ex.printStackTrace();
			valid.setLogByException(ex);
			return valid;
		} finally {
			fileName = null;
			file = null;
			// System.out.println("valid: " + AppUtil.jsonObtenerStrDesdeObjeto(valid));
			valid = null;
		}
	}

	@Override
	public ValidatorModel translateFile(OrderLepton order) {
		ValidatorModel valid = new ValidatorModel("storeFile");
		try {
			initFilePaths();
			String path = "C:\\Users\\Jhon-PC\\Documents\\BiesseFile\\" + order.getfileName();
			OrderBiesse orderBiesse = mappingToBiesse(order);
			createBiesseFile(orderBiesse, path);
			// Path fileStorageLocation = Paths.get(path);
			// Path filePath = fileStorageLocation.resolve(fileName).normalize();
			// Resource resource = new UrlResource(fileStorageLocation.toUri());

			valid.setObj(path);
			valid.setSuccess(true);
		} catch (Exception ex) {
			ex.printStackTrace();
			valid.setSuccess(false);
			valid.setLogByException(ex);
		}
		return valid;
	}

	public void initFilePaths() throws IOException {
		UPLOADS_FILE_LOCATION = "C:\\Users\\Jhon-PC\\Documents" + separator + "uploads" + separator;
		UPLOADS_FILE_PATH = Paths.get(UPLOADS_FILE_LOCATION);
		createPathIfNotExist(UPLOADS_FILE_PATH);

		ACCOUNT_STATE_FILE_LOCATION = UPLOADS_FILE_LOCATION + "leptonXml" + separator;
		ACCOUNT_STATE_FILE_PATH = Paths.get(ACCOUNT_STATE_FILE_LOCATION);
		createPathIfNotExist(ACCOUNT_STATE_FILE_PATH);

		DOWNLOADS_FILE_LOCATION = "C:\\Users\\Jhon-PC\\Documents" + separator + "BiesseFile" + separator;
		DOWNLOADS_FILE_PATH = Paths.get(DOWNLOADS_FILE_LOCATION);
		createPathIfNotExist(DOWNLOADS_FILE_PATH);
	}

	public void createPathIfNotExist(Path path) throws IOException {
		// System.out.println("createPathIfNotExist: " +
		// path.toFile().getAbsolutePath());
		if (!Files.exists(path)) {
			Files.createDirectory(path);
		}
	}

	public OrderLepton mappingLeptonXml(String path, String fileName)
			throws ParserConfigurationException, SAXException, Exception {

		OrderLepton response = new OrderLepton();
		WorkList workList = new WorkList();

		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		File file = new File(path);
		dbf.setValidating(false);
		DocumentBuilder db = dbf.newDocumentBuilder();
		Document document = db.parse(file);
		document.getDocumentElement().normalize();
		// ORDER
		NodeList nList = document.getElementsByTagName("Order");
		for (int temp = 0; temp < nList.getLength(); temp++) {
			Node nNode = nList.item(temp);
			if (nNode.getNodeType() == Node.ELEMENT_NODE) {
				Element eElement = (Element) nNode;
				response = new OrderLepton(eElement.getAttribute("ID"), eElement.getAttribute("Code"), fileName, null);
				// WORKLIST
				NodeList mList = eElement.getElementsByTagName("WorkList");
				for (int mTemp = 0; mTemp < mList.getLength(); mTemp++) {
					Node mNode = mList.item(mTemp);
					if (mNode.getNodeType() == Node.ELEMENT_NODE) {
						Element meElement = (Element) mNode;
						workList = new WorkList(meElement.getAttribute("ID"), meElement.getAttribute("Code"),
								meElement.getAttribute("Released"), meElement.getAttribute("Due"),
								meElement.getAttribute("MaterialID"), meElement.getAttribute("Comment"), null, null);
						// PART
						NodeList oList = meElement.getElementsByTagName("Part");
						ArrayList<Part> part = new ArrayList<>();
						for (int oTemp = 0; oTemp < oList.getLength(); oTemp++) {
							Node oNode = oList.item(oTemp);
							if (oNode.getNodeType() == Node.ELEMENT_NODE) {
								Element oeElement = (Element) oNode;
								String qMin = oeElement.getAttribute("qMin");
								String code = oeElement.getAttribute("Code");
								Part partX = new Part(
										!oeElement.getAttribute("id").isEmpty() && oeElement.getAttribute("id") != null
												? Integer.parseInt(oeElement.getAttribute("id"))
												: 0,
										!qMin.isEmpty() && qMin != null ? Integer.parseInt(qMin) : 0,
										!code.isEmpty() && code != null ? Integer.parseInt(code) : 0,
										oeElement.getAttribute("info1"), oeElement.getAttribute("info2"),
										oeElement.getAttribute("ean13"), oeElement.getAttribute("detalle"),
										oeElement.getAttribute("material"),
										oeElement.getAttribute("veta") != null
												&& !oeElement.getAttribute("veta").isEmpty() ? true : false,
										!oeElement.getAttribute("nPiezas").isEmpty()
												&& oeElement.getAttribute("nPiezas") != null
														? Integer.parseInt(oeElement.getAttribute("nPiezas"))
														: 0,
										!oeElement.getAttribute("largo").isEmpty()
												&& oeElement.getAttribute("largo") != null
														? Float.parseFloat(oeElement.getAttribute("largo"))
														: 0,
										!oeElement.getAttribute("ancho").isEmpty()
												&& oeElement.getAttribute("ancho") != null
														? Float.parseFloat(oeElement.getAttribute("ancho"))
														: 0,
										oeElement.getAttribute("lamA1"), oeElement.getAttribute("lamB2"),
										oeElement.getAttribute("lamA2"), oeElement.getAttribute("lamB1"),
										!oeElement.getAttribute("esp").isEmpty()
												&& oeElement.getAttribute("esp") != null
														? Float.parseFloat(oeElement.getAttribute("esp"))
														: 0,
										oeElement.getAttribute("canCara"),
										!oeElement.getAttribute("dbl").isEmpty()
												&& oeElement.getAttribute("dbl") != null
														? Float.parseFloat(oeElement.getAttribute("dbl"))
														: 0,
										!oeElement.getAttribute("canPrf").isEmpty()
												? Float.parseFloat(oeElement.getAttribute("canPrf"))
												: 0,
										oeElement.getAttribute("canA1"), oeElement.getAttribute("canB2"),
										oeElement.getAttribute("canA2"), oeElement.getAttribute("canB1"),
										!oeElement.getAttribute("dmt").isEmpty()
												&& oeElement.getAttribute("dmt") != null
														? Float.parseFloat(oeElement.getAttribute("dmt"))
														: 0,
										oeElement.getAttribute("perfCara"),
										!oeElement.getAttribute("perfPrf").isEmpty()
												&& oeElement.getAttribute("perfPrf") != null
														? Float.parseFloat(oeElement.getAttribute("perfPrf"))
														: 0,
										!oeElement.getAttribute("dbs").isEmpty()
												&& oeElement.getAttribute("dbs") != null
														? Float.parseFloat(oeElement.getAttribute("dbs"))
														: 0,
										!oeElement.getAttribute("dbi").isEmpty()
												&& oeElement.getAttribute("dbi") != null
														? Float.parseFloat(oeElement.getAttribute("dbi"))
														: 0,
										oeElement.getAttribute("perfA1"), oeElement.getAttribute("perfB2"),
										oeElement.getAttribute("perfA2"), oeElement.getAttribute("perfB1"),
										oeElement.getAttribute("sisCara"), oeElement.getAttribute("sisA"),
										oeElement.getAttribute("sisB"));
								part.add(partX);
							}
						}
						workList.setPart(part);
						// JOB
						NodeList pList = meElement.getElementsByTagName("Job");
						ArrayList<Job> job = new ArrayList<>();
						for (int pTemp = 0; pTemp < pList.getLength(); pTemp++) {
							Node pNode = pList.item(pTemp);
							if (pNode.getNodeType() == Node.ELEMENT_NODE) {
								Element peElement = (Element) pNode;
								Job jobX = new Job(peElement.getAttribute("ID"), peElement.getAttribute("Code"),
										peElement.getAttribute("Type"), peElement.getAttribute("ToWork"),
										peElement.getAttribute("QBoards"), peElement.getAttribute("ExeOrder"), null,
										null, null);
								// BOARD
								NodeList qList = peElement.getElementsByTagName("Board");
								Board board = new Board();
								for (int qTemp = 0; qTemp < qList.getLength(); qTemp++) {
									Node qNode = qList.item(qTemp);
									if (qNode.getNodeType() == Node.ELEMENT_NODE) {
										Element qeElement = (Element) qNode;
										String id = qeElement.getAttribute("id");
										String large = qeElement.getAttribute("L");
										String width = qeElement.getAttribute("W");
										String thickness = qeElement.getAttribute("Thickness");
										board = new Board(!id.isEmpty() && id != null ? Integer.parseInt(id) : 0,
												qeElement.getAttribute("Code"),
												!large.isEmpty() && large != null ? Float.parseFloat(large) : 0,
												!width.isEmpty() && width != null ? Float.parseFloat(width) : 0,
												!thickness.isEmpty() && thickness != null ? Float.parseFloat(thickness)
														: 0,
												qeElement.getAttribute("MatNo"), qeElement.getAttribute("MatCode"),
												qeElement.getAttribute("Grain"));
									}
								}
								// PIECE
								NodeList rList = peElement.getElementsByTagName("Piece");
								ArrayList<Piece> piece = new ArrayList<>();
								for (int rTemp = 0; rTemp < rList.getLength(); rTemp++) {
									Node rNode = rList.item(rTemp);
									if (rNode.getNodeType() == Node.ELEMENT_NODE) {
										Element reElement = (Element) rNode;
										String id = reElement.getAttribute("id");
										String large = reElement.getAttribute("L");
										String width = reElement.getAttribute("W");
										Piece pieceX = new Piece(reElement.getAttribute("N"),
												!id.isEmpty() && id != null ? Integer.parseInt(id) : 0,
												!large.isEmpty() && large != null ? Float.parseFloat(large) : 0,
												!width.isEmpty() && width != null ? Float.parseFloat(width) : 0,
												reElement.getAttribute("Q"), reElement.getAttribute("QPartt"));
										piece.add(pieceX);
									}
								}
								// PROGRAM
								NodeList sList = peElement.getElementsByTagName("Program");
								Program program = new Program();
								for (int sTemp = 0; sTemp < sList.getLength(); sTemp++) {
									Node sNode = sList.item(sTemp);
									if (sNode.getNodeType() == Node.ELEMENT_NODE) {
										Element seElement = (Element) sNode;
										// CUT
										NodeList tList = seElement.getElementsByTagName("Cut");
										ArrayList<Cut> cut = new ArrayList<>();
										for (int tTemp = 0; tTemp < tList.getLength(); tTemp++) {
											Node tNode = tList.item(tTemp);
											if (tNode.getNodeType() == Node.ELEMENT_NODE) {
												Element teElement = (Element) tNode;
												String id = teElement.getAttribute("id");
												String large = teElement.getAttribute("L");
												Cut cutX = new Cut(
														!id.isEmpty() && id != null ? Integer.parseInt(id) : 0,
														teElement.getAttribute("Code"),
														!large.isEmpty() && large != null ? Float.parseFloat(large) : 0,
														teElement.getAttribute("Rep"));
												cut.add(cutX);
											}
										}
										program.setCut(cut);
									}
								}
								jobX.setProgram(program);
								jobX.setPiece(piece);
								jobX.setBoard(board);
								job.add(jobX);
							}
						}
						workList.setJob(job);
					}
				}
				response.setWorkList(workList);
			}
		}
		return response;
	}

	public OrderBiesse mappingToBiesse(OrderLepton order) throws Exception {
		ArrayList<PartBiesse> part = new ArrayList<>();
		for (Part partLepton : order.getWorkList().getPart()) {
			PartBiesse partBiesse = new PartBiesse(partLepton.getId(), partLepton.getLargo(), partLepton.getAncho(),
					partLepton.getqMin(), partLepton.getCode(), partLepton.getInfo1(), partLepton.getInfo2(),
					partLepton.getDetalle(), partLepton.getEan13(), partLepton.getLamA1(), partLepton.getLamB2(),
					partLepton.getLamA2(), partLepton.getLamB2());
			part.add(partBiesse);
		}
		WorkListBiesse workListBiesse = new WorkListBiesse(order.getWorkList().getId(), order.getWorkList().getCode(),
				order.getWorkList().getReleased(), order.getWorkList().getDue(), order.getWorkList().getMaterialId(),
				order.getWorkList().getComment(), part, order.getWorkList().getJob());
		OrderBiesse orderBiesse = new OrderBiesse(order.id, order.code, workListBiesse);
		return orderBiesse;
	}

	public void createBiesseFile(OrderBiesse orderBiesse, String path) {
		try {
			DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
			// Elemento Order
			Document doc = docBuilder.newDocument();
			Element rootElement = doc.createElement("Order");
			doc.appendChild(rootElement);
			Attr orderID = doc.createAttribute("ID");
			orderID.setValue(orderBiesse.getId());
			rootElement.setAttributeNode(orderID);
			Attr orderCode = doc.createAttribute("Code");
			orderCode.setValue(orderBiesse.getCode());
			rootElement.setAttributeNode(orderCode);

			// WorkList
			Element workList = doc.createElement("WorkList");
			rootElement.appendChild(workList);
			Attr workListID = doc.createAttribute("ID");
			workListID.setValue(orderBiesse.workList.getId());
			workList.setAttributeNode(workListID);
			Attr workListCode = doc.createAttribute("Code");
			workListCode.setValue(orderBiesse.getWorkList().getCode());
			workList.setAttributeNode(workListCode);
			Attr workListReleased = doc.createAttribute("Released");
			workListReleased.setValue(orderBiesse.getWorkList().getReleased());
			workList.setAttributeNode(workListReleased);
			Attr workListDue = doc.createAttribute("Due");
			workListDue.setValue(orderBiesse.getWorkList().getDue());
			workList.setAttributeNode(workListDue);
			Attr workListMaterialID = doc.createAttribute("MaterialID");
			workListMaterialID.setValue(orderBiesse.getWorkList().getMaterialId());
			workList.setAttributeNode(workListMaterialID);
			Attr workListComment = doc.createAttribute("Comment");
			workListComment.setValue(orderBiesse.getWorkList().getComment());
			workList.setAttributeNode(workListComment);

			// Part
			for (PartBiesse partX : orderBiesse.getWorkList().getPart()) {
				Element part = doc.createElement("Part");
				workList.appendChild(part);
				Attr partId = doc.createAttribute("id");
				partId.setValue(partX.getId().toString());
				part.setAttributeNode(partId);
				Attr partL = doc.createAttribute("L");
				partL.setValue(String.valueOf(partX.getL()));
				part.setAttributeNode(partL);
				Attr partW = doc.createAttribute("W");
				partW.setValue(String.valueOf(partX.getW()));
				part.setAttributeNode(partW);
				Attr partQMin = doc.createAttribute("qMin");
				partQMin.setValue(partX.getqMin().toString());
				part.setAttributeNode(partQMin);
				Attr partCode = doc.createAttribute("Code");
				partCode.setValue(partX.getCode().toString());
				part.setAttributeNode(partCode);
				Attr partIDesc = doc.createAttribute("IDesc");
				partIDesc.setValue(partX.getIDesc());
				part.setAttributeNode(partIDesc);
				Attr partIIDesc = doc.createAttribute("IIDesc");
				partIIDesc.setValue(partX.getIIDesc());
				part.setAttributeNode(partIIDesc);
				Attr partInsLam = doc.createAttribute("InsLam");
				partInsLam.setValue(partX.getInsLam());
				part.setAttributeNode(partInsLam);
				Attr partCabInfo = doc.createAttribute("CabInfo");
				partCabInfo.setValue(partX.getCabInfo());
				part.setAttributeNode(partCabInfo);
				Attr partMatEdgeL = doc.createAttribute("MatEdgeL");
				partMatEdgeL.setValue(partX.getMatEdgeL());
				part.setAttributeNode(partMatEdgeL);
				Attr partMatEdgeUp = doc.createAttribute("MatEdgeUp");
				partMatEdgeUp.setValue(partX.getMatEdgeUp());
				part.setAttributeNode(partMatEdgeUp);
				Attr partMatEdgeR = doc.createAttribute("MatEdgeR");
				partMatEdgeR.setValue(partX.getMatEdgeR());
				part.setAttributeNode(partMatEdgeR);
				Attr partMatEdgeLo = doc.createAttribute("MatEdgeLo");
				partMatEdgeLo.setValue(partX.getMatEdgeLo());
				part.setAttributeNode(partMatEdgeLo);
			}

			// Job
			for (Job jobX : orderBiesse.getWorkList().getJob()) {
				Element job = doc.createElement("Job");
				workList.appendChild(job);
				Attr jobId = doc.createAttribute("ID");
				jobId.setValue(jobX.getId());
				job.setAttributeNode(jobId);
				Attr jobCode = doc.createAttribute("Code");
				jobCode.setValue(jobX.getCode());
				job.setAttributeNode(jobCode);
				Attr jobType = doc.createAttribute("Type");
				jobType.setValue(jobX.getType());
				job.setAttributeNode(jobType);
				Attr jobToWork = doc.createAttribute("ToWork");
				jobToWork.setValue(jobX.getToWork());
				job.setAttributeNode(jobToWork);
				Attr jobQBoard = doc.createAttribute("QBoard");
				jobQBoard.setValue(jobX.getqBoards());
				job.setAttributeNode(jobQBoard);
				Attr jobExeOrder = doc.createAttribute("ExeOrder");
				jobExeOrder.setValue(jobX.getExeOrder());
				job.setAttributeNode(jobExeOrder);

				// Board
				Element board = doc.createElement("Board");
				job.appendChild(board);
				Attr boardId = doc.createAttribute("id");
				boardId.setValue(String.valueOf(jobX.getBoard().getId()));
				board.setAttributeNode(boardId);
				Attr boardCode = doc.createAttribute("Code");
				boardCode.setValue(jobX.getBoard().getCode());
				board.setAttributeNode(boardCode);
				Attr boardL = doc.createAttribute("L");
				boardL.setValue(String.valueOf(jobX.getBoard().getLarge()));
				board.setAttributeNode(boardL);
				Attr boardW = doc.createAttribute("W");
				boardW.setValue(String.valueOf(jobX.getBoard().getWidth()));
				board.setAttributeNode(boardW);
				Attr boardThickness = doc.createAttribute("Thickness");
				boardThickness.setValue(String.valueOf(jobX.getBoard().getThickness()));
				board.setAttributeNode(boardThickness);
				Attr boardMatNo = doc.createAttribute("MatNo");
				boardMatNo.setValue(jobX.getBoard().getMatNo());
				board.setAttributeNode(boardMatNo);
				Attr boardMatCode = doc.createAttribute("MatCode");
				boardMatCode.setValue(jobX.getBoard().getMatCode());
				board.setAttributeNode(boardMatCode);
				Attr boardGrain = doc.createAttribute("Grain");
				boardGrain.setValue(jobX.getBoard().getGrain());
				board.setAttributeNode(boardGrain);
				// Piece
				for (Piece pieceX : jobX.getPiece()) {
					Element piece = doc.createElement("Piece");
					job.appendChild(piece);
					Attr pieceN = doc.createAttribute("N");
					pieceN.setValue(pieceX.getN());
					piece.setAttributeNode(pieceN);
					Attr pieceId = doc.createAttribute("id");
					pieceId.setValue(String.valueOf(pieceX.getId()));
					piece.setAttributeNode(pieceId);
					Attr pieceL = doc.createAttribute("L");
					pieceL.setValue(String.valueOf(pieceX.getLarge()));
					piece.setAttributeNode(pieceL);
					Attr pieceW = doc.createAttribute("W");
					pieceW.setValue(String.valueOf(pieceX.getWidth()));
					piece.setAttributeNode(pieceW);
					Attr pieceQ = doc.createAttribute("Q");
					pieceQ.setValue(pieceX.getQ());
					piece.setAttributeNode(pieceQ);
					Attr pieceQPatt = doc.createAttribute("QPatt");
					pieceQPatt.setValue(pieceX.getqPatt());
					piece.setAttributeNode(pieceQPatt);
				}

				// Program
				Element program = doc.createElement("Program");
				job.appendChild(program);

				// Cut
				for (Cut cutX : jobX.getProgram().getCut()) {
					Element cut = doc.createElement("Cut");
					program.appendChild(cut);
					Attr cutId = doc.createAttribute("id");
					cutId.setValue(String.valueOf(cutX.getId()));
					cut.setAttributeNode(cutId);
					Attr cutCode = doc.createAttribute("Code");
					cutCode.setValue(cutX.getCode());
					cut.setAttributeNode(cutCode);
					Attr cutL = doc.createAttribute("L");
					cutL.setValue(String.valueOf(cutX.getLarge()));
					cut.setAttributeNode(cutL);
					Attr cutRep = doc.createAttribute("Rep");
					cutRep.setValue(cutX.getRep());
					cut.setAttributeNode(cutRep);
				}
			}

			// Se escribe el contenido del XML en un archivo
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			DOMSource source = new DOMSource(doc);
			StreamResult result = new StreamResult(new File(path));
			transformer.transform(source, result);
		} catch (ParserConfigurationException pce) {
			pce.printStackTrace();
		} catch (TransformerException tfe) {
			tfe.printStackTrace();
		}
	}

	@Override
	public ResponseEntity<Resource> getFile(String fileName) throws Exception {
		//System.out.println("fileName in getFile: " + fileName);
		initFilePaths();
		Path fileStorageLocation = Paths.get("C:\\Users\\Jhon-PC\\Documents\\BiesseFile\\" + fileName);
		Resource resource = new UrlResource(fileStorageLocation.toUri());
		return ResponseEntity.ok()
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
				.body(resource);
	}
}
